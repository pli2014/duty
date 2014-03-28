/**
 * 
 */
package vo.table;

import org.apache.commons.lang3.StringUtils;

/**
 * Table Header, map property <b>Column</b> of <b>dataTable</b>
 * 
 * @author gudong
 * @since $Date:2014-02-16$
 */
public class TableHeaderVo {

  private String mData = null; // field Name support object graph, like platform.details.0
  private String sTitle = null; // head title
  private String sClass = ""; // if you want to support mobile, please use 'hidden-phone';
  private String[] asSorting = new String[] { "asc", "desc" }; // [ "desc", "asc","asc", null ]
  private boolean bSortable = false;
  private boolean bSearchable = false;
  private boolean bVisible = true;
  
  // // additional properties
  private String[][] searchOptions = null; // map html select element, like [["1","2"]["Male","Female"]]
  
  public TableHeaderVo(String mData, String sTitle) {
    super();
    this.mData = mData;
    this.sTitle = sTitle;
  }

  public TableHeaderVo(String mData, String sTitle, boolean bVisible) {
    super();
    this.mData = mData;
    this.sTitle = sTitle;
    this.bVisible = bVisible;
    if (bVisible == false) {
      this.bSortable = false;
      this.bSearchable = false;
    }
  }

  public String getmData() {
    return mData;
  }

  public void setmData(String mData) {
    this.mData = mData;
  }

  public String getsTitle() {
    return sTitle;
  }

  public void setsTitle(String sTitle) {
    this.sTitle = sTitle;
  }

  public String[] getAsSorting() {
    return asSorting;
  }

  public void setAsSorting(String[] asSorting) {
    this.asSorting = asSorting;
  }

  public boolean isbSortable() {
    return bSortable;
  }

  public void setbSortable(boolean bSortable) {
    this.bSortable = bSortable;
  }

  public boolean isbSearchable() {
    return bSearchable;
  }

  public TableHeaderVo disableSearch() {
    this.setbSearchable(false);
    return this;
  }

  public TableHeaderVo enableSearch() {
    this.setbSearchable(true);
    return this;
  }
  
  public void setbSearchable(boolean bSearchable) {
    this.bSearchable = bSearchable;
  }

  public boolean isbVisible() {
    return bVisible;
  }

  public void setbVisible(boolean bVisible) {
    this.bVisible = bVisible;
  }

  public void setSearchOptions(String[][] searchOptions) {
    this.searchOptions = searchOptions;
  }

  public TableHeaderVo addSearchOptions(String[][] searchOptions) {
    this.searchOptions = searchOptions;
    return this;
  }

  public String getSClass() {
    return sClass;
  }

  public TableHeaderVo setSClass(String sClass) {
    this.sClass = sClass;
    return this;
  }
  
  public TableHeaderVo hidePhone(){
    this.sClass = "hidden-phone";
    return this;
  }
  
}
