var host = "http://localhost:8081"

 //下单
function save_order(id){
   	var url = host +"/api/v1/order/add?video_id="+id
   	console.info(url)
    		$("#pay_img").attr("src",url);
 }

/*头部导航栏的输入框*/
$(function(){
	/*获取cookies*/


	function get_list(){
		$.ajax({
	        type: 'get',
	        url: host+"/api/v1/video/page?size=60",
	        dataType: 'json',
	        success: function(res){
	        	
	        	var data = res.data;
	        	for(var i=0;i<data.length;i++){
	        		var video =  data[i];
	        		var price = video.price/100;
	        		var template = "<div class='col-sm-6 col-md-3'><div class='thumbnail'>"+
		        	"<img src='"+video.coverImg+"'alt='通用的占位符缩略图'>"+
		        	"<div class='caption'><h3>"+video.title+"</h3><p>价格:"+price+"元</p>"+
		        	"<p><a href='' onclick='save_order("+video.id+")' data-toggle='modal' data-target='#myModal' class='btn btn-primary' role='button'>立刻购买</a></p></div></div></div>"
		        	$(".row").append(template)	
	        	}
	        	 
	        }})
	}


	//获取微信登录地址
	function get_wechat_login(){
		var current_page = window.location.href;
		$.ajax({
	        type: 'get',
	        url: host+"/api/v1/wechat/login_url?access_page="+current_page,
	        dataType: 'json',
	        success: function(res){	        	
	        	var data = res.data;
	        	$("#login").attr("href",data);
	        }})
	}

	//定义一个方法获取url中"?"符后的字串  
	function get_params() {   
       var url = window.location.search; //获取url中"?"符后的字串   
	   var theRequest = new Object();   
	   if (url.indexOf("?") != -1) {   
		  var str = url.substr(1);   
		  strs = str.split("&");   
		  for(var i = 0; i < strs.length; i ++) {   
			  //就是这句的问题
			 theRequest[strs[i].split("=")[0]]=decodeURI(strs[i].split("=")[1]); 
			 //之前用了unescape()
			 //才会出现乱码  
		  }   
	   }   
	   return theRequest;
    }   


    //获取url上的头像和昵称
    function set_user_info(){
    	var user_info = get_params()
    	if(JSON.stringify(user_info)!='{}'){
	    	var name = encodeURI(user_info['name'])
	    	var head_img = user_info['head_img']
	    	var token = user_info['token']
	    	$("#login").html(decodeURI(name))
	    	$("#head_img").attr("src",head_img);
	    	$.cookie('token', token, { expires: 7, path: '/' });
    	}else{
    		//$("#login").html("请登录")
    	}
    }


	get_wechat_login()
	get_list();
	set_user_info()
});
