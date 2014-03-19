<!DOCTYPE html>
<html lang="en">
<%@ include file="../commonHeader.jsp"%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Mosaddek">
    <meta name="keyword" content="FlatLab, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
    <link rel="shortcut icon" href="img/favicon.png">

    <title>Data Table</title>
  </head>
<body>
<section class="panel">
       <!-- page start-->
  <s:if test="tableTitle != null && tableTitle.length() > 0">
  <header class="panel-heading">${tableTitle}</header>
  </s:if>
  <div class="panel-body">
       <div class="row">
            <div class="col-lg-6 col-md-6" style="width: 150px; margin-bottom: 15px;">
                <a class="btn btn-success" href="window.location.href = '${actionPrex}/add.action'">
                    <i class="fa fa-plus"></i>
                    添加
                </a>
            </div>
            
           <form class="form-inline" style="">
               <div class="form-group">
                   <label class="pull-left control-label"  >名称</label>
                   <div class="col-lg-9 col-md-9 filter-component-column">
                       <input type="text" class="form-control input-sm filter-component" >
                   </div>
               </div>
               <div class="form-group"  >
                   <label class="pull-left control-label" >创建时间</label>
                   <div class="col-lg-9 col-md-9 filter-component-column">
                       <input type="text" class="form-control input-sm filter-component" >
                   </div>
               </div>
               <div class="form-group" >
                   <label class="pull-left control-label" >~</label>
                   <div class="col-lg-9 col-md-9 filter-component-column">
                       <input type="text" class="form-control input-sm filter-component" >
                   </div>
               </div>
              <div class="form-group"  >
                   <label class="pull-left control-label" >更新时间</label>
                   <div class="col-lg-9 col-md-9 filter-component-column">
                       <input type="text" class="form-control input-sm filter-component" >
                   </div>
               </div>
               <div class="form-group" >
                   <label class="pull-left control-label" >~</label>
                   <div class="col-lg-9 col-md-9 filter-component-column">
                       <input type="text" class="form-control input-sm filter-component" >
                   </div>
               </div>
                <a class="btn btn-success pull-right" style="margin-right:20px;margin-bottom: 15px; ">
                    <i class="fa fa-check"></i>
                    查询
                </a>
           </form>
          
        </div>
  
        <div class="adv-table dataTables_wrapper form-inline">
            <table  id="${tableId}"   class="table table-striped table-advance table-hover display  table-bordered"> </table>
       </div>
 </div>
</section>
     <!-- page end-->
    
<script type="text/javascript">
     var idName;
     var tableId = "${tableId}";
	 var actionPrex = "${actionPrex}";
     var cellFormatter = {};
      /*ALL OPTIONS*/
     var options = {
        'edit':{
	       'title':'修改', 
	       'html': '<button title="修改" class="btn btn-primary btn-xs" onclick="options[\'edit\'].onClick(this)"><i class="fa fa-pencil"></i></button>',
	       'onClick' : function(button){
	           var tableObj = $('#'+tableId).dataTable();
	           var nTr = $(button).parents('tr')[0];
	           var selectRowData =  tableObj.fnGetData( nTr );
	           window.location.href = actionPrex + "/edit.action?id=" + selectRowData[idName];
	       }     
        },
        'delete': {
	       'title':'删除',
	       'html' : '<button title="删除" class="btn btn-danger btn-xs" onclick="options[\'delete\'].onClick(this)"><i class="fa fa-trash-o "></i></button>',
	       'onClick' : function(button){
	           if (confirm("您确定要删除吗?")){
		           var tableObj = $('#'+tableId).dataTable();
		           var nTr = $(button).parents('tr')[0];
		           var selectRowData =  tableObj.fnGetData( nTr );
		           window.location.href = actionPrex + "/delete.action?id=" + selectRowData[idName];
	           }
	       }
        }  
    }
      /* Formating function for row details */
      function fnFormatDetails ( oTable, nTr ){
          var aData = oTable.fnGetData( nTr );
          var aoColumns = oTable.fnSettings().aoColumns;
          var sOut = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">';
          for(var i=0;i<aoColumns.length;i++){
              if(aoColumns[i].bVisible == false){
                  sOut += '<tr><td>'+ aoColumns[i].sTitle+':</td><td>'+aData[aoColumns[i].mData]+'</td></tr>';
              }
          }
          sOut += '</table>';
          return sOut;
      }
 
 $(document).ready(function() {
     var tableUrl = "${actionPrex}/initTable.action";
     var param = {};
     $.getJSON( tableUrl, param, function (initParam) { 
         idName = initParam.idName;
         for(var i=0;i<initParam.aoColumns.length ; i++){
             if(typeof cellFormatter[initParam.aoColumns[i].mData] == "function"){
                 initParam.aoColumns[i].mRender = cellFormatter[initParam.aoColumns[i].mData];
             }
         }
	     /*
	      * Initialse DataTables, with no sorting on the 'details' column
	      */
	     var oTable = $('#${tableId}').dataTable( {
	         "bProcessing": initParam.bProcessing,
	 		 "bServerSide": initParam.bServerSide,
	 		 "iDisplayLength":initParam.iDisplayLength,
	 		 "aLengthMenu": initParam.aLengthMenu,
	 		 "aoColumns": initParam.aoColumns,
	 		 "sAjaxSource": "${actionPrex}/queryTable.action",
	 		 //"sDom": '<"H"lT><"clear">rt<"F"ip>',
	 		 "sDom": 'rt<"F"ip>',
	 		 "oLanguage": {
	 		     "oPaginate": {
			        "sPrevious": "上一页",
			        "sNext":"下一页"
			    },
	            "sLengthMenu": "每页显示 _MENU_ 条",
	            "sZeroRecords": "无数据",
	            "sInfo": "显示第 _START_ 到 _END_ 条, 共 _TOTAL_ 条.",
	            "sInfoEmpty": "无数据",
	            "sInfoFiltered": "(filtered from _MAX_ total records)"
	         },
		     "fnDrawCallback": function ( oSettings ) {
		            if(initParam.hasDetails > 0){
		                // show details
		                if($('#${tableId} thead tr th:first[arias="showDetails"]').length == 0){
		                    $('#${tableId} thead tr').each( function () {
		                          var thObj =document.createElement( 'th' );
		                          thObj.setAttribute("arias","showDetails");
			                      this.insertBefore(thObj , this.childNodes[0] );
			                 } );
		                }
		                
		                var nCloneTd = document.createElement( 'td' );
		                nCloneTd.innerHTML = '<img class="operation" src="<%=request.getContextPath()%>/jslib/flatlab/assets/advanced-datatable/examples/examples_support/details_open.png">';
		                nCloneTd.className = "center";
			            $('#${tableId} tbody tr').each( function (i) {
			                this.insertBefore(  nCloneTd.cloneNode( true ) , this.childNodes[0] );
			            } );
			            $('#${tableId} tbody td').on('click','img.operation',function(){
			                var nTr = $(this).parents('tr')[0];
			                if ( oTable.fnIsOpen(nTr) ){
			                    // This row is already open - close it 
			                     $(this).attr("src" , "<%=request.getContextPath()%>/jslib/flatlab/assets/advanced-datatable/examples/examples_support/details_open.png");
			                     oTable.fnClose( nTr );
			                }else{
			                   //   Open this row 
			                     $(this).attr("src" , "<%=request.getContextPath()%>/jslib/flatlab/assets/advanced-datatable/examples/examples_support/details_close.png");
			                     oTable.fnOpen( nTr, fnFormatDetails(oTable, nTr), 'details' );
			                     $('td.details',$(nTr).next()).attr("colspan",nTr.childNodes.length);
			                 }
			            } );
		            }
		             // add options
	            if($('#${tableId} thead tr th:last[arias="options"]').length == 0){
                    $('#${tableId} thead tr').each( function () {
                          var thObj =document.createElement( 'th' );
                          thObj.setAttribute("arias","options");
                          $(this).append(thObj);
	                 } );
                    
	                 $('#${tableId} tbody tr').each( function (i) {
	                    var nCloneTd = document.createElement( 'td' );
	                    $(this).append(nCloneTd);
	                    for(var p in options){
	                      $(nCloneTd).append(options[p].html);
	                    }
		            });
                }
		     }, 
	         "fnServerData": function ( sSource, aoData, fnCallback, oSettings ) {
	             /* //======= method one===========
	             // Add some extra data to the sender 
	 			aoData.push( { "name": "more_data", "value": "my_value" } );
	 			$.getJSON( sSource, aoData, function (json) { 
	 				// Do whatever additional processing you want on the callback, then tell DataTables
	 				fnCallback(json)
	 			} );
	 			 //======= method one END=========== */
	 			     
	 			//========method two==================   
	 			 var mDataObj = {};
	 			 var sortObj = {};
	 			 var iMax = 0;
	 			for(var n=0;n<aoData.length;n++){
	 			    if(aoData[n].name == "iColumns"){
	 			       iMax = aoData[n].value;
	 			    }
	 			    if(aoData[n].name == "mDataProp_0"){
	 			      for(var i = 0; i < iMax;i++){
	 			         mDataObj[aoData[n].name] = aoData[n].value;
	 			         n++;
	 			      }
	 			    }
	 			    if(aoData[n].name == "iSortCol_0"){
	 			      for(var i = 0; i < iMax;i++){
	 			          if(aoData[n].name == "iSortCol_"+i){
	 			               sortObj[mDataObj["mDataProp_"+ aoData[n].value]] = aoData[++n].value;
	 			               n++;
	 			          }else{
	 			              break;
	 			          }
	 			      }
	 			  }
	 			}
	 			for(var p in sortObj){
	 			    aoData.push( { "name": "sort['"+p+"']", "value": sortObj[p] } );
	 			}
	 			$('#${tableId} thead tr th input[type="text"]').each( function (i) {
	 			  aoData.push( { "name": "filter['"+this.name+"']", "value": this.value } );
	 			});
	 			$('#${tableId} thead tr th select[name]').each( function (i) {
	 			  aoData.push( { "name": "filter['"+this.name+"']", "value": $(this).val() } );
		 		});
	 			 oSettings.jqXHR = $.ajax( {
	                 "dataType": 'json',
	                 "type": "POST",
	                 "url": sSource,
	                 "data": aoData,
	                 "success": function(result,status,response){
	                    // Do whatever additional processing you want on the callback, then tell DataTables
	                     fnCallback(result);
	                  }
	               } );
	 			//========method two END==================   
	          }
	        });
 	} );
 } );
  </script>
  <s:if test="customJs != null && customJs.length() > 0">
     <script src="${customJs}" type="text/javascript"></script>
  </s:if>
       <script>

           // 格式化js时间
           var formatDateTime = function (obj, IsMi) {
               var myDate = new Date(obj);
               var year = myDate.getFullYear();
               var month = ("0" + (myDate.getMonth() + 1)).slice(-2);
               var day = ("0" + myDate.getDate()).slice(-2);
               var h = ("0" + myDate.getHours()).slice(-2);
               var m = ("0" + myDate.getMinutes()).slice(-2);
               var s = ("0" + myDate.getSeconds()).slice(-2);
               var mi = ("00" + myDate.getMilliseconds()).slice(-3);
               if (IsMi == true) {
                   return year + "-" + month + "-" + day + " " + h + ":" + m + ":" + s;
               }
               else {
                   return year + "-" + month + "-" + day + " " + h + ":" + m + ":" + s + "." + mi;
               }
           };

           //格式化createTime  modifyTime
           cellFormatter["modifyTime"]=cellFormatter["createTime"] = function ( data, type, full ) {
               if(data!=null){
                return formatDateTime(data.time,true);
               }else{
                   return "";
               }
           }

       </script>
  </body>
</html>