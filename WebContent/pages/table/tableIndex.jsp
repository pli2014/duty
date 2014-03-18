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
    
    <style type="text/css">
		 div.dataTables_length{
		    padding: 5px 0 !important;
		    margin: 0px !important;
		 }
		 div.DTTT_container{
		    padding: 5px 0 !important;
		    margin: 0px !important;
		 }
		 div.dataTables_info{
		    padding: 5px 0 !important;
		    margin: 0px !important;
		 }
		 div.dataTables_paginate{
		    padding: 5px 0 !important;
		    margin: 0px !important;
		 }
		 .btn {
		  color: #ffffff !important;
		  border-color: #c5c5c5 !important;
		  border-color: rgba(0, 0, 0, 0.15) rgba(0, 0, 0, 0.15) rgba(0, 0, 0, 0.25) !important;
		}
		.btn-me {
		  color: #ffffff !important;
		  padding:8px 15px !important;
		  font-size: 120% !important;
		  text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25) !important;
		  background-color: #5bb75b !important;
		  background-image: -moz-linear-gradient(top, #62c462, #51a351) !important;
		  background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#62c462), to(#51a351)) !important;
		  background-image: -webkit-linear-gradient(top, #62c462, #51a351) !important;
		  background-image: -o-linear-gradient(top, #62c462, #51a351) !important;
		  background-image: linear-gradient(to bottom, #62c462, #51a351) !important;
		  background-repeat: repeat-x !important;
		  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff62c462', endColorstr='#ff51a351', GradientType=0) !important;
		  border-color: #51a351 #51a351 #387038 !important;
		  border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25) !important;
		  *background-color: #51a351 !important;
		  /* Darken IE7 buttons by default so they stand out more given they won't have borders */
		
		  filter: progid:DXImageTransform.Microsoft.gradient(enabled = false) !important;
		}
		.btn-me:hover,
		.btn-me:active,
		.btn-me.active,
		.btn-me.disabled,
		.btn-me[disabled] {
		  color: #ffffff !important;
		  background-color: #51a351 !important;
		  *background-color: #499249 !important;
		}
		.btn-me.active {
		  background-color: #408140 \9 !important;
		}
    </style>

    <title>Data Table</title>
  </head>
<body>
       <!-- page start-->
  <s:if test="tableTitle != null && tableTitle.length() > 0">
  <header class="panel-heading">${tableTitle}</header>
  </s:if>
  <div class="panel-body">
        
  
  
        <div class="adv-table">
            <table  id="${tableId}" cellpadding="0" cellspacing="0" border="0" class="display table table-bordered">
              <thead>
              </thead>
             <tbody>
                 <tr><td colspan="4" class="dataTables_empty">正在加载数据...</td></tr>
             </tbody>
                 
            </table>
       </div>
 </div>
     <!-- page end-->
    
<script type="text/javascript">
     var idName;
     var tableId = "${tableId}";
	 var actionPrex = "${actionPrex}";
     var cellFormatter = {};
     var actions = [
              {
                  "sButtonText":"<span style='color:white;'><i class='fa fa-plus' style='margin-right:8px;'></i>添加</span>",
                  "sExtends":"text",
                  "sButtonClass": "btn btn-me",
                  "fnClick": function ( nButton, oConfig, oFlash ) {
                      window.location.href = actionPrex + "/add.action";
                  }
              },{
                  "sButtonText":"<span style='color:white;'><i class='fa fa-edit' style='margin-right:8px;'></i>修改</span>",
                  "sExtends":"select_single",
                  "sButtonClass": "btn btn-me",
                  "fnClick": function ( nButton, oConfig, oFlash ) {
                      if($(nButton).hasClass("DTTT_disabled")){
			              return;
			          }
                      var tableObj = $('#'+tableId).dataTable();
                      var selectedRows = tableObj.$('tr.DTTT_selected');
                      if(selectedRows.length != 1){
                          alert("请选择一行记录!");
                      }else{
                         var selectRowData =  tableObj.fnGetData( selectedRows[0] );
                          window.location.href = actionPrex + "/edit.action?id=" + selectRowData[idName];
                      }
                  }
              },{
                  "sButtonText":"<span style='color:white;'><i class='fa fa-trash-o' style='margin-right:8px;'></i>删除</span>",
                  "sExtends":"select",
                  "sButtonClass": "btn btn-me",
                  "fnClick": function ( nButton, oConfig, oFlash ) {
                      if($(nButton).hasClass("DTTT_disabled")){
			              return;
			          }
                      var tableObj = $('#'+tableId).dataTable();
                      var selectedRows = tableObj.$('tr.DTTT_selected');
                      if(selectedRows.length == 0){
                          alert("请选择行记录!");
                      }else{
                         if (confirm("您确定要删除"+ selectedRows.length +"条记录吗?")){
						    var par = "";
						    tableObj.$('tr.DTTT_selected').each(function(i){
						        par = par + "ids="+tableObj.fnGetData(this)[idName] + "&";
						    });
                            window.location.href = actionPrex + "/delete.action?" + par;
						 }
                      }
                  }
              }
      ];
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
	 		 "sDom": '<"H"T><"clear">rt<"F"ip>',
	 		 "oTableTools": {
	 		   "sRowSelect": "multi",
		       "aButtons": actions
	 		 },
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