<%@ include file="/pages/commonHeader.jsp"%>
  <section id="container" >

      <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <style type="text/css">
                  ul.sidebar-menu li a{
                      color:'';
                      backgroud-color:yellow;
                      font-size:15px;
                  }
                  ul.sidebar-menu li ul.sub li a.active{
                      color:red;
                      backgroud-color:yellow;
                  }
              </style>
              <ul class="sidebar-menu" id="nav-accordion">
                  <li class="sub-menu">
                      <a href="javascript:;">
                          <i class="fa fa-laptop"></i>
                          <span>志愿者管理</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="backend/volunteer/index.action">志愿者管理</a></li>
                          <li><a  href="backend/volunteerVerify/index.action">审核</a></li>
                          <li><a  href="backend/volunteerInterview/index.action">面试</a></li>
                      </ul>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;">
                          <i class="fa fa-laptop"></i>
                          <span>服务管理</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="backend/serviceplace/serviceplacelist.action?type=0">院内服务地点</a></li>
                          <li><a  href="backend/serviceplace/serviceplacelist.action?type=1">院外服务地点</a></li>
                      </ul>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;">
                          <i class="fa fa-laptop"></i>
                          <span>培训管理</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="backend/traincourse/traincourselist.action">培训课程</a></li>
                          <li><a  href="backend/volunterTrainCourse/index.action">培训记录</a></li>
                      </ul>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;">
                          <i class="fa fa-laptop"></i>
                          <span>工时管理</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="backend/report/activeTimeReport.action">工时实时统计</a></li>
                          <li><a  href="backend/report/volunteerDailyReport.action">志愿者日工时统计</a></li>
                          <li><a  href="backend/report/volunteerMonthlyReport.action">志愿者月工时统计</a></li>
                          <%--<li><a  href="backend/report/serviceDailyReport.action">服务地点日工时统计</a></li>--%>
                          <%--<li><a  href="backend/report/serviceMonthlyReport.action">服务地点月工时统计</a></li>--%>
                          <%--<li><a  href="boxed_page.html">工时排名</a></li>--%>
                      </ul>
                  </li>
                  <s:if test="#session.backendSessionUser != null && #session.backendSessionUser.name == 'admin'">
                      <li class="sub-menu">
                          <a href="javascript:;">
                              <i class="fa fa-laptop"></i>
                              <span>后台系统管理</span>
                          </a>
                          <ul class="sub">
                              <li><a  href="backend/user/index.action">后台用户管理</a></li>
                              <li><a  href="backend/systemsetting.action">系统参数设定</a></li>
                          </ul>
                      </li>
                  </s:if>
                  <li>
                      <a  href="backend/logout.action">
                          <i class="fa fa-user"></i>
                          <span>用户退出</span>
                      </a>
                  </li>

                  <!--multi level menu end-->

              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->
  </section>