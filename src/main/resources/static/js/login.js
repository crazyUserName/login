  $("#sub").click(function(){
	  
	  		var userName = $('#userName').val();
	  		if(userName == "" ){
	  			alert("用户名不能为空");
	  			 return;
	  		}
	  		
	  		var password = $('#password').val();
	  		if(password == ""){
	  			alert("密码不能为空");
	  			 return;
	  		}
	  		
	  	var rememberMe = false;
          var encrypt = new JSEncrypt();
          encrypt.setPublicKey($('#publicKey').val());
          var encrypted = encrypt.encrypt(password);
          
          var data = {
        		  userName : userName,
        		  password : encrypted,
        		  rememberMe:rememberMe
          };
          
		 $.post("/user/login", data, function(result){
			 if(result.flag){
				 window.location.href = "/goIndex";
			 }else{
				 alert(result.msg);
			 }
		 });
			 }); 