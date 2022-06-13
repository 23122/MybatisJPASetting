/**
 * 
 */
 
 $(function(){
	
	document.getElementById("btn-b").addEventListener("click", function(){
	//서버로 요청해서 현재 로그인중인지 체크
		$.ajax({
			url:"/member/signin-check",
			success:function(result){
				if(result){
					//true이면: 게시판페이지 이동
					location.href="/board";
				}else{
					//권한이 없으면
					alert("로그인후 이용가능합니다.");
					location.href="/signin";
				}
			}
		});
	});
});