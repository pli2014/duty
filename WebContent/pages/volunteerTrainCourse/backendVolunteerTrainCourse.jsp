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
		 table tbody tr.even.row_selected td{
			background-color: #B0BED9 !important;
		 }
    </style>
    <!--external css-->
    <title>
     <s:if test="volunteer.id.length() > 0">
        修改培训记录
      </s:if>
      <s:else>
        添加培训记录
      </s:else>
    </title>
  </head>
<body>

<!--main content start-->
  <section class="panel" style="padding-left: 15px;">
    <header class="panel-heading">
       <s:if test="volunteer.id.length() > 0">
        修改培训记录
      </s:if>
      <s:else>
        添加培训记录
      </s:else>
    </header>
    <s:actionerror/><s:actionmessage/>
    <div class="form-group has-success">
        <label class="col-lg-2 control-label">志愿者名</label>
        <div class="col-lg-10">
            <input type="hidden" id="volunteerId" />
            <input type="text" id="volunteerName" placeholder="志愿者名"  class="form-control pull-left" style=" width: 200px;"/>
            <input type="button" class="btn btn-success" name="" value="查询">       
            <select class="form-control input-lg m-bot15">
            </select>
        </div>
    </div> 
        
    <div class="form-group has-success">
        <label class="col-lg-2 control-label">状态</label>
        <div class="col-lg-10">
            <select class="form-control input-lg m-bot15">
              <option value="0">未通过</option>
              <option value="1">通过</option>
            </select>
        </div>
    </div>
    
    <div class="form-group has-success">
        <label class="col-lg-2 control-label">培训课程</label>
        <div class="col-lg-10">
           <input type="hidden" id="trainCourseId" />
            <input type="text" id="trainCourseName" placeholder="培训课程"   class="form-control  pull-left" style=" width: 200px;"/>
          <input type="button" class="btn btn-success" name="" value="查询">   
           <select class="form-control input-lg m-bot15">
            </select>
        </div>
    </div>
         
         
  </section>
  <script type="text/javascript">
  $(document).ready(function() {
	  
	  
  });
  </script>
</body>
</html>