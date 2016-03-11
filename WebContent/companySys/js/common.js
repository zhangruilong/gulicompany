function del(url,message){
	if(confirm("是否"+message)){
		parent.main.location.href = url;
	}
	
}
