//页面初始化
function init() {
    getAllUSer();
}
/**
 * 获取所有用户数据
 * get请求： 路径后加属性,
 * post请求： contentType: "application/json; charset=utf-8",
 */
function getAllUSer() {
    console.log("获取所有用户数据！");
    // 定义接收数据
    $.ajax({
        url: 'http://119.23.176.137:9000/easy_to_stop/user/getAllUser',
        type: "get",
        dataType: "json",
        cache: false,
        crossDomain: true,
        processData: false,
        success: function (res) {
            console.log(res);
            let data=[];
            for(var i=0; i<res.data.length; i++){
                var userId=res.data[i].userId;
                var nickname=res.data[i].nickname;
                var password=res.data[i].password;
                var gender=res.data[i].gender;
                var age=res.data[i].age;
                var address=res.data[i].address;
                var balance=res.data[i].balance;
                var gmtCreate=res.data[i].gmtCreate;
                // console.log("用户已经获取！+"+userId);
                //给表格元素赋值
                data.push({userId,nickname,password,gender,age,address,balance,gmtCreate});
            }
            console.log("接收的数据为："+data.toString());
            console.log("接收的数据为："+data[1].userId);
            //把数据渲染到页面中
            // 格式化成对象
            // var jsObject=JSON.parse(res.data);
            // console.log("接收的数据为(js)："+jsObject);//如图无需再转JSON了，可以直接使用。
            var str="";
            for(var i=0;i<res.data.length;i++){
                str+="<tr>" +
                    "<td style='text-align: center'>"+i+"</td>" +
                    "<td>"+res.data[i].userId+"</td>" +
                    "<td>"+res.data[i].nickname+"</td>" +
                    "<td>"+res.data[i].password+"</td>" +
                    "<td>"+res.data[i].gender+"</td>" +
                    "<td>"+res.data[i].age+"</td>" +
                    "<td>"+res.data[i].address+"</td>" +
                    "<td>"+res.data[i].balance+"</td>" +
                    "<td>"+res.data[i].gmtCreate+"</td>" +
                    "<td>"+res.data[i].balance+"</td>" +
                    "</tr>";
            }
            document.getElementById("user_tbody").innerHTML=str;
        }, error: function (res) {
            console.log(res);
        }
    })
}


