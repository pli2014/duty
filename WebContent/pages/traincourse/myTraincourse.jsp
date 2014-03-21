<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../metrouiHeader.jsp" %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Mosaddek">
    <meta name="keyword" content="FlatLab, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
    <link rel="shortcut icon" href="img/favicon.png">
    
    
    <!-- Bootstrap core CSS -->
      <link href="jslib/flatlab/css/bootstrap.min.css" rel="stylesheet">
      <link href="jslib/flatlab/css/bootstrap-reset.css" rel="stylesheet">
      <!--external css-->
      <link href="jslib/flatlab/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
      <link href="jslib/flatlab/assets/advanced-datatable/media/css/demo_page.css" rel="stylesheet" />
      <link href="jslib/flatlab/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet" />
      <link href="jslib/flatlab/assets/data-tables/DT_bootstrap.css" rel="stylesheet" />
      <link href="jslib/flatlab/assets/advanced-datatable/extras/TableTools/media/css/TableTools.css" rel="stylesheet" />
      <link href="jslib/flatlab/assets/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
      <!-- Custom styles for this template -->
      <link href="jslib/flatlab/css/style.css" rel="stylesheet">
      <link href="jslib/flatlab/css/style-responsive.css" rel="stylesheet" />
      <link href="jslib/jquery-ui-1.10.4.custom/css/start/jquery-ui-1.10.4.custom.min.css" rel="stylesheet" />
     
     
     <script src="jslib/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>
      <script src="jslib/flatlab/js/bootstrap.min.js"></script>
      <script class="include" type="text/javascript" src="jslib/flatlab/js/jquery.dcjqaccordion.2.7.js"></script>
      <script src="jslib/flatlab/js/jquery.scrollTo.min.js"></script>
      <script src="jslib/flatlab/js/jquery.nicescroll.js" type="text/javascript"></script>
      <script src="jslib/flatlab/js/respond.min.js" ></script>

      <!-- js placed at the end of the document so the pages load faster -->
      <script src="jslib/flatlab/assets/advanced-datatable/media/js/jquery.dataTables.js" type="text/javascript" language="javascript" ></script>
      <script src="jslib/flatlab/assets/data-tables/DT_bootstrap.js" type="text/javascript" ></script>
      <!--common script for all pages-->
      <script src="jslib/flatlab/assets/advanced-datatable/extras/TableTools/media/js/ZeroClipboard.js" type="text/javascript" charset="utf-8" ></script>
      <script src="jslib/flatlab/assets/advanced-datatable/extras/TableTools/media/js/TableTools.js" type="text/javascript" charset="utf-8" ></script>
      <script src="jslib/flatlab/assets/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript" charset="utf-8" ></script>
      
    
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
		 
		 .metro .received:before {
	       content: "已接受的培训";
	     }
	     
	     .metro .unreceived:before {
	       content: "未接受的培训";
	     }
	     
	     .DTTT_selected{
	       background-color: yellow !important;
	     }
    </style>
    <title>我的培训</title>
  </head>
<body class="metro">
  <div class="container">
        <h1>
            <a href="index.action"><i class="icon-arrow-left-3 fg-darker smaller"></i></a>
            我的培训<small class="on-right"></small>
        </h1>
        
		  <h2 id="__table__">已接受的培训</h2>
           <div class="example received">
              <table  id="receivedTrainCourse" cellpadding="0" cellspacing="0" border="0" class="table striped bordered hovered">
                <thead>
                </thead>
               <tbody>
                   <tr><td colspan="4" class="dataTables_empty">正在加载数据...</td></tr>
               </tbody>
                   
              </table>
          </div>
          
           <h2 id="__table__">未接受的培训</h2>
           <div class="example unreceived">
              <table  id="unreceivedTrainCourse" cellpadding="0" cellspacing="0" border="0" class="table striped bordered hovered">
                <thead>
                </thead>
               <tbody>
                   <tr><td colspan="4" class="dataTables_empty">正在加载数据...</td></tr>
               </tbody>
                   
              </table>
          </div>
  </div> 
  
  <script type="text/javascript"> 
   
//格式化js时间
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
 
  
   $(document).ready(function() {
       
     var cellFormatter = {};
     
     var chineseMessage = {
 		     "oPaginate": {
		        "sPrevious": "上一页",
		        "sNext":"下一页"
		    },
            "sLengthMenu": "每页显示 _MENU_ 条",
            "sZeroRecords": "无数据",
            "sInfo": "显示第 _START_ 到 _END_ , 共 _TOTAL_ 条.",
            "sInfoEmpty": "无数据",
            "sInfoFiltered": "(filtered from _MAX_ total records)"
         };
     var tableUrl = "${actionPrex}/initTable.action";
     var param = {};
      $.getJSON( tableUrl, param, function (initParam) { 
           
	     var receivedTable = $('#receivedTrainCourse').dataTable( {
	         "bProcessing": initParam.bProcessing,
	 		 "bServerSide": initParam.bServerSide,
	 		 "iDisplayLength":initParam.iDisplayLength,
	 		 "aLengthMenu": initParam.aLengthMenu,
	 		 "aoColumns": [{
	 		     "mData" : "trainCourse.name",
	 		     "sTitle" : "课程名称",
	 		     "bSortable": false,
	 		     "bSearchable": false
	 		   },{
	 		      "mData" : "status",
	 		     "sTitle" : "状态",
	 		     "bSortable": false,
	 		     "bSearchable": false,
	 		     "mRender":function ( data, type, full ) {
				    if(data == 0){
				        return '未通过';
				    }else if(data == 1){
				        return '通过';
				    }else{
				        return '未知';
				    }
				 }
	 		  }],
	 		 "sAjaxSource": "${actionPrex}/queryMyTrainCourse.action",
	 		 //"sDom": '<"H"lT><"clear">rt<"F"ip>',
	 		 "sDom": '<"H"lT><"clear">rt<"F"ip>',
	 		 "bSort": false,
	 		 "oTableTools": {
	 		   "sRowSelect": "multi",
	 		   "aButtons":[]
	 		 },
	 		 "oLanguage": chineseMessage
	        });
	     
	     cellFormatter["status"] = function ( data, type, full ) {
		    if(data == 0){
		        return '创建';
		    }else if(data == 1){
		        return '开始';
		    }else if(data == 2){
		        return '结束';
		    }else{
		        return '未知状态';
		    }
		}
	   //格式化createTime  modifyTime
	     cellFormatter["modifyTime"]=cellFormatter["createTime"] = function ( data, type, full ) {
	         if(data!=null){
	          return formatDateTime(data.time,true);
	         }else{
	             return "";
	         }
	     }
	     
	     for(var i=0;i<initParam.aoColumns.length ; i++){
             if(typeof cellFormatter[initParam.aoColumns[i].mData] == "function"){
                 initParam.aoColumns[i].mRender = cellFormatter[initParam.aoColumns[i].mData];
             }
         }
	     
	     var unreceivedTable = $('#unreceivedTrainCourse').dataTable( {
	         "bProcessing": initParam.bProcessing,
	 		 "bServerSide": initParam.bServerSide,
	 		 "iDisplayLength":initParam.iDisplayLength,
	 		 "aLengthMenu": initParam.aLengthMenu,
	 		 "aoColumns": initParam.aoColumns,
	 		 "sAjaxSource": "${actionPrex}/queryTable.action",
	 		 //"sDom": '<"H"lT><"clear">rt<"F"ip>',
	 		 "sDom": '<"H"lT><"clear">rt<"F"ip>',
	 		 "bSort": false,
	 		 "oTableTools": {
	 		   "sRowSelect": "multi",
		       "aButtons": [{
	                 "sButtonText":"接受",
	                 "sExtends":"select",
	                 "fnClick": function ( nButton, oConfig, oFlash ) {
	                     if($(nButton).hasClass("DTTT_disabled")){
			              return;
			          }
	                     var tableObj = $('#unreceivedTrainCourse').dataTable();
	                     var selectedRows = tableObj.$('tr.DTTT_selected');
	                     if(selectedRows.length == 0){
	                         alert("请选择行记录!");
	                     }else{
	                        if (confirm("您确定要接受"+ selectedRows.length +"培训课程吗?")){
						    var par = "";
						    tableObj.$('tr.DTTT_selected').each(function(i){
						        par = par + "ids="+tableObj.fnGetData(this)["id"] + "&";
						    });
	                           window.location.href = "${actionPrex}/receive.action?" + par;
						 }
	                     }
	                 }
	             }]
	 		 },
	 		 "oLanguage": chineseMessage
	        });
         });
     });
   </script>
  </body>
</html>