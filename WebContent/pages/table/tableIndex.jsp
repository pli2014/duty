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
           <form class="form-horizontal tasi-form">

               <div id="tableTools" class="form-group">
                   <div class="col-lg-3">
                       <a class="btn btn-success" href="${actionPrex}/add.action?${addButtonParameter}">
                           <i class="fa fa-plus"></i>
                           添加
                       </a>
                   </div>
               </div>
           <section class="panel">
               <header class="panel-heading" onclick="$('#panelbody').toggle();$('#panelbodybullet').toggleClass('fa fa-chevron-up');$('#panelbodybullet').toggleClass('fa fa-chevron-down');" style="cursor: pointer">
                   查询区域
                      <span class="tools pull-right">
                        <span id="panelbodybullet" class="fa fa-chevron-up" style="cursor: pointer"></span>
                      </span>
               </header>
               <div class="panel-body" id="panelbody" style="display: none">
                   <div class="form-group">
                       <label class="col-lg-1 control-label">名称</label>
                       <div class="col-lg-2">
                           <input id="search_name" type="text" class="form-control">
                       </div>
                   </div>
                   <div class="form-group">
                       <label class="col-lg-1 control-label">创建时间</label>
                       <div class="col-lg-2">
                           <input id="createTimeStart"   data-date-format="yyyy-mm-dd" type="text" class="form-control" ondblclick="$(this).val('')">
                           <script>
                               $("#createTimeStart").datepicker();
                           </script>
                       </div>
                       <div class="col-lg-2">
                           <input id="createTimeEnd"   data-date-format="yyyy-mm-dd" type="text" class="form-control" ondblclick="$(this).val('')">
                           <script>
                               $("#createTimeEnd").datepicker();
                           </script>
                       </div>

                       <label class="col-lg-1 control-label">更新时间</label>
                       <div class="col-lg-2">
                           <input id="modifyTimeStart"  data-date-format="yyyy-mm-dd" type="text" class="form-control" ondblclick="$(this).val('')">
                           <script>
                               $("#modifyTimeStart").datepicker();
                           </script>
                       </div>
                       <div class="col-lg-2">
                           <input id="modifyTimeEnd"  data-date-format="yyyy-mm-dd" type="text" class="form-control" ondblclick="$(this).val('');">
                           <script>
                               $("#modifyTimeEnd").datepicker();
                           </script>
                       </div>
                       <a class="btn btn-success pull-right" style="margin-left:5px" onclick="$('#${tableId}').dataTable()._fnAjaxUpdate()">
                           <i class="fa fa-check"></i>
                           查询
                       </a>
                       <a class="btn btn-success pull-right" onclick="jQuery('form input').val('');$('#${tableId}').dataTable()._fnAjaxUpdate()">
                           <i class="fa fa-check"></i>
                           重置
                       </a>
                   </div>
              </div>
            </section>
           </form>
          
        <div class="adv-table dataTables_wrapper form-inline">
            <table  id="${tableId}"   class="table table-striped table-advance table-hover display  table-bordered"> </table>
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
	       'html': '<button title="修改" style="margin-left:5px" class="btn btn-primary btn-xs" onclick="options[\'edit\'].onClick(this)"><i class="fa fa-pencil"></i></button>',
	       'onClick' : function(button){
	           var tableObj = $('#'+tableId).dataTable();
	           var nTr = $(button).parents('tr')[0];
	           var selectRowData =  tableObj.fnGetData( nTr );
	           window.location.href = actionPrex + "/edit.action?${addButtonParameter}&id=" + selectRowData[idName];
	       }     
        },
        'delete': {
	       'title':'删除',
	       'html' : '<button title="删除" style="margin-left:5px" class="btn btn-danger btn-xs" onclick="options[\'delete\'].onClick(this)"><i class="fa fa-trash-o "></i></button>',
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
          var sOut = '<table cellpadding="5" cellspacing="0" border="0">';
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
         if(initParam.disableTools){
             $('#tableTools').css('display','none');
         }
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
	 		 "sAjaxSource": "${actionPrex}/queryTable.action?${addButtonParameter}",
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
		                nCloneTd.innerHTML = '<img class="operation" src="jslib/flatlab/assets/advanced-datatable/examples/examples_support/details_open.png">';
		                nCloneTd.className = "center";
			            $('#${tableId} tbody tr').each( function (i) {
			                this.insertBefore(  nCloneTd.cloneNode( true ) , this.childNodes[0] );
			            } );
			            $('#${tableId} tbody td').on('click','img.operation',function(){
			                var nTr = $(this).parents('tr')[0];
			                if ( oTable.fnIsOpen(nTr) ){
			                    // This row is already open - close it 
			                     $(this).attr("src" , "jslib/flatlab/assets/advanced-datatable/examples/examples_support/details_open.png");
			                     oTable.fnClose( nTr );
			                }else{
			                   //   Open this row 
			                     $(this).attr("src" , "jslib/flatlab/assets/advanced-datatable/examples/examples_support/details_close.png");
			                     oTable.fnOpen( nTr, fnFormatDetails(oTable, nTr), 'details' );
			                     $('td.details',$(nTr).next()).attr("colspan",nTr.childNodes.length);
			                 }
			            } );
		            }
		             // add options
		        var totalOptions = 0;
		        for(var p in options){
		            totalOptions++;
		        }
	            if(totalOptions > 0 && $('#${tableId} thead tr th:last[arias="options"]').length == 0){
                    $('#${tableId} thead tr').each( function () {
                          var thObj =document.createElement( 'th' );
                          thObj.setAttribute("arias","options");
                          thObj.innerHTML ="操作";
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
	 			if($('#search_name').val() != ''){
	 			 aoData.push( { "name": "filter['name']", "value": $('#search_name').val() } ); 
	 		    }
	 			if($('#createTimeStart').val() != ''){
	 			 aoData.push( { "name": "filter['createTime_gteq']", "value": $('#createTimeStart').val() } );
	 			}
	 		    if($('#createTimeEnd').val() != ''){
	 			 aoData.push( { "name": "filter['createTime_lteq']", "value": $('#createTimeEnd').val() } );
	 		    }
	 		    if($('#modifyTimeStart').val() != ''){
	 			 aoData.push( { "name": "filter['modifyTime_gteq']", "value": $('#modifyTimeStart').val() } );
	 			}
	 		    if($('#modifyTimeEnd').val() != ''){
	 			 aoData.push( { "name": "filter['modifyTime_lteq']", "value": $('#modifyTimeEnd').val() } );
	 		    }
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
                   return year + "-" + month + "-" + day;
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