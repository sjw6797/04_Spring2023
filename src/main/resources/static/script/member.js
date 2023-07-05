function loginCheck() {
	if ($('#id').val().length == 0) {
		alert('아이디를 입력하세요')
		return false
	} else if ($('#pwd').val().length == 0) {
		alert('비밀번호를 입력하세요')
		return false
	}
	return true
}

$(function(){
	var result = 0;
	$('#wrap').click(function(){
		if(result == 1){
			$('#topmodel').css({'display' : 'none'})	
		}
		if(result == 0){
			$('#topmodel').css({'display' : 'block'})	
			result = 1
		}
	})
	
	$('#topbutton').click(function(){
		$('#topmodel').css({'display' : 'block'})	
		result = 0;
	})
})

