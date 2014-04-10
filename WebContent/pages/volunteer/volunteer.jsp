<%--
  User: peter
  Date: 14-4-10
  Time: 下午8:32
--%>
<%@ taglib uri="/struts-tags" prefix="s" %>
    <script>
        cellFormatter["occupation"] = function ( data, type, full ) {
           <s:iterator value="listSource">
               if(data =='<s:property value="code"/>'){
                    return '<s:property value="name"/>';
               }
           </s:iterator>
               return "错误信息("+data+")";
        }
    </script>