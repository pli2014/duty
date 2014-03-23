/** 
 * cellFormatter
 */
cellFormatter["name"] = function ( data, type, full ) {
   return '<img disable style="margin-right:10px" class="volunteerimg" src="'+full.iconpath+'" width="60px" height="50px" onerror="this.src=\'img/volunteer.png\'"/>' + data;
}
cellFormatter["sex"] = function ( data, type, full ) {
    if(data == 1){
        return '男';
    }else if(data == 2){
        return '女';
    }else{
       return '未知';
    } 
}
cellFormatter["status"] = function ( data, type, full ) {
    //0=已注册、1=已审核、2=已面试、3=正在服务期、4=已注销
    if(data == 0){
        return '已注册';
    }else if(data == 1){
        return '已审核';
    }else if(data = 2){
       return '已面试';
    }else if(data == 3){
        return '正在服务期';
    }else if(data == 4){
        return '已注销';
    }else{
       return '未知';
    } 
}
cellFormatter["registerFrom"] = function ( data, type, full ) {
    //1=医院,2=微信
    if(data == 1){
        return '医院';
    }else if(data == 2){
        return '微信';
    }else{
       return '未知';
    } 
}

options = {
        'pass':{
           'title':'面试', 
           'html': '<button title="面试" style="margin-left:5px" class="btn btn-primary btn-xs" onclick="options[\'pass\'].onClick(this)"><i class="fa fa-pencil"></i></button>',
           'onClick' : function(button){
               if (confirm("您确定要面试吗?")){
                   var tableObj = $('#'+tableId).dataTable();
                   var nTr = $(button).parents('tr')[0];
                   var selectRowData =  tableObj.fnGetData( nTr );
                   window.location.href = actionPrex + "/interview.action?id=" + selectRowData[idName];
               }
           }     
        }
};

jQuery("#decoratebody").on("mouseover",".volunteerimg",function(){
    jQuery(this).css({width:'300px',height:'200px',position:'absolute'});
});
jQuery("#decoratebody").on("mouseout",".volunteerimg",function(){
    jQuery(this).css({width:'60px',height:'50px',position:'static'});
});