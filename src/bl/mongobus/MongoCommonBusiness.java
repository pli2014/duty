package bl.mongobus;

import java.beans.PropertyDescriptor;
import java.util.*;

import bl.common.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;

import org.mongodb.morphia.query.Query;
import vo.table.TableDataVo;
import vo.table.TableQueryVo;
import bl.beans.Bean;
import bl.exceptions.MiServerException;

import com.mongodb.WriteResult;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import dao.MongoDBConnectionFactory;

public class MongoCommonBusiness<F, L> implements BusinessInterface, TableBusinessInterface {
    private static Logger LOG = LoggerFactory.getLogger(MongoCommonBusiness.class);
    // currently, we use a single database for all data.
    protected String dbName = "form";

    protected Class<L> clazz = null;

    @Override
    public BeanContext constructLeafBean() {
        return null;
    }

    @Override
    public BusinessResult createLeaf(BeanContext genLeafBean) {
        Datastore dc = MongoDBConnectionFactory.getDatastore(this.dbName);
        ((Bean) genLeafBean).setCreateTime(new Date(System.currentTimeMillis()));
        dc.save(genLeafBean);
        BusinessResult br = new BusinessResult();
        return br;
    }

    @Override
    public BusinessResult getLeaf(String objectId) {
        Datastore dc = MongoDBConnectionFactory.getDatastore(this.dbName);
        Object object = dc.find(this.clazz, "_id", new ObjectId(objectId)).get();
        BusinessResult br = new BusinessResult();
        br.setResponseData(object);
        return br;
    }

    @Override
    public BusinessResult deleteLeaf(String objectId) {
        Datastore dc = MongoDBConnectionFactory.getDatastore(this.dbName);
        BusinessResult br = new BusinessResult();
        Object obj = this.getLeaf(objectId).getResponseData();
        if (obj != null) {
            WriteResult wr = dc.delete(obj);
            if (wr.getError() != null) {
                throw new MiServerException.General("error.mongodb.writedata", wr.getError());
            }
        }

        return br;
    }

    @Override
    public BusinessResult updateLeaf(BeanContext origBean, BeanContext newBean) {
        Datastore dc = MongoDBConnectionFactory.getDatastore(this.dbName);
        BusinessResult br = new BusinessResult();
        ((Bean) newBean).setCreateTime(((Bean) origBean).getCreateTime());
        ((Bean) newBean).setModifyTime(new Date(System.currentTimeMillis()));
        Key<BeanContext> key = dc.save(newBean);
        br.setResponseData(key.getId());
        return br;
    }

    @Override
    public BusinessResult getAllLeaves() {
        Datastore dc = MongoDBConnectionFactory.getDatastore(this.dbName);
        List<L> list = dc.find(this.clazz).asList();
        BusinessResult br = new BusinessResult();
        br.setResponseData(list);
        return br;
    }

    @Override
    public void deleteByCondition(Map filter) {
        Datastore dc = MongoDBConnectionFactory.getDatastore(this.dbName);
        Query<L> query = this.constructQuery(filter, null, null);
        dc.delete(query);
    }

    /**
     * 一般的.filter(criteria, value)语法被支持。标准语法是属性名和操作("field >",
     * or "field in")的整合。所有的语法被逻辑"and" 暗暗的联系在一起。
     *
     * Sort（排序）
     *    你可以通过一个或多个属性名对结果进行升序或降序排序
     *
     *    ... // desc order
     *    Query q = ds.createQuery(MyEntity.class).filter("foo >", 12).order("-dateAdded");
     *    ... // asc dateAdded, desc foo
     *    Query q = ds.createQuery(MyEntity.class).filter("foo >", 12).order("dateAdded, -foo");
     *
     *    Offset(skip) and limit(size) like as Mysql
     *    你可以通过在查询是指定一个偏移值是服务器跳过一些记录元素。
     *    这将比使用几个属性的范围进行查询要低效的多。如下所示：
     *
     * @param filter
     * @param sorted
     * @param spc
     * @return
     */
    @Override
    public List queryDataByCondition(Map filter, Set sorted, SpecPaginationContext spc) {
        Query query = this.constructQuery(filter, sorted, spc);
        return query.asList();
    }

    @Override
    public List queryDataByCondition(Map filter, Set sorted) {
        return queryDataByCondition(filter, sorted, null);
    }

    private Query<L> constructQuery(Map<String,String> filter, Set<String> sorted, SpecPaginationContext spc) {
        Datastore dc = MongoDBConnectionFactory.getDatastore(this.dbName);
        Query query = dc.createQuery(this.clazz);
        if (filter != null && !filter.isEmpty()) {
            Iterator<String> iterator = filter.keySet().iterator();
            Object obj = null;
            try {
                obj = Class.forName(this.clazz.getName()).newInstance();
            } catch (Exception e) {
                LOG.error("this exception [#0]", e.getMessage());
            }
            while (iterator.hasNext()) {
                String key = iterator.next();
                String value = filter.get(key);
                if (value != null && !value.isEmpty() && !value.equals("-1")) {
                    try {
                        BeanUtils.setProperty(obj,key,value);
                        query.filter(key, PropertyUtils.getProperty(obj, key));
                    }catch (Exception e){
                        LOG.error("this exception [#0]", e.getMessage());
                    }
                }
            }
        }

        if (sorted != null && !sorted.isEmpty()) {
            Iterator<String> iterator = sorted.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                query.order(key);
            }
        }

        if(spc!=null){
            query.offset(spc.getLimitOffset());
            query.limit(spc.getLimitSize());
        }
        return query;
    }

    @Override
    public BusinessResult getLeafByName(String name) {
        Datastore dc = MongoDBConnectionFactory.getDatastore(this.dbName);
        BusinessResult br = new BusinessResult();
        br.setResponseData(dc.find(this.clazz, "name", name).get());
        return br;
    }

    @Override
    public TableDataVo query(TableQueryVo queryParam) {
        Datastore dc = MongoDBConnectionFactory.getDatastore(this.dbName);
        Set<String> sortedMappingMongo = new HashSet<String>();
        LinkedHashMap<String, String> lhm = queryParam.getSort();
        if(lhm!=null){
            Iterator<String> it = lhm.keySet().iterator();
            while(it.hasNext()){
                String key = it.next();
                String value = lhm.get(key);
                if(value!=null && !value.isEmpty()){
                    if(value.equals("asc")){
                        sortedMappingMongo.add(key);
                    }else{
                        sortedMappingMongo.add("-"+key);
                    }
                }
            }
        }
        SpecPaginationContext spc = new SpecPaginationContext();
        spc.setLimitOffset(queryParam.getIDisplayStart());
        spc.setLimitSize(queryParam.getIDisplayLength());
        List<L> dataRecord = queryDataByCondition(queryParam.getFilter(),sortedMappingMongo,spc);
        TableDataVo dataTable = new TableDataVo();
        dataTable.setsEcho(queryParam.getSEcho());
        dataTable.setAaData(dataRecord);
        return dataTable;
    }

    @Override
    public long getCount(TableQueryVo queryParam) {
        Datastore dc = MongoDBConnectionFactory.getDatastore(this.dbName);

        Set<String> sortedMappingMongo = new HashSet<String>();
        LinkedHashMap<String, String> lhm = queryParam.getSort();
        if (lhm != null) {
            Iterator<String> it = lhm.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                String value = lhm.get(key);
                if (value != null && !value.isEmpty()) {
                    if (value.equals("asc")) {
                        sortedMappingMongo.add(key);
                    } else {
                        sortedMappingMongo.add("-" + key);
                    }
                }
            }
        }
        Query<L> query = this.constructQuery(queryParam.getFilter(), sortedMappingMongo, null);
        return dc.getCount(query);
    }

}
