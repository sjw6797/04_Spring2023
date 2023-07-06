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
	var result = 1;
	var result1 = 1;
	
	$('#topbutton').click(function(){
		$('#topmodel').css({'display' : 'block'})	
		result = 0;
		return
	})
	
	$('#getPoint').click(function(){
		$('#myPageDiv').css({'display' : 'block'})	
		result1 = 0;
		return
	})
	
	$('#wrap').click(function(){
		if(result == 1){
			$('#topmodel').css({'display' : 'none'})	
		}
		
		if(result1 == 1){
			$('#myPageDiv').css({'display' : 'none'})	
		}
		
		if(result == 0){
			$('#topmodel').css({'display' : 'block'})	
			result = 1
		}
		
		if(result1 == 0){
			$('#myPageDiv').css({'display' : 'block'})	
			result1 = 1
		}
	})
	
	$('#topbutton').click(function(){
		$('#topmodel').css({'display' : 'block'})	
		result = 0;
		return
	})
	
	$('#getPoint').click(function(){
		$('#myPageDiv').css({'display' : 'block'})	
		result1 = 0;
		return
	})
	
})

// 해상도별로 자바스크립트를 다르게 설정
	if (matchMedia("screen and (min-width: 1000px)").matches) {
		function roundtrip() {
			$('#comback').css({"display":"block"})
			$('.inputcontent').css({"width":"16.3%"})
	
		}
	
		function oneway() {
			$('#comback').css({"display":"none"})
			$('.inputcontent').css({"width":"19.6%"})
		}
		
	} else {
		function roundtrip() {
			$('#comback').css({"display":"block"})
			$("#datepicker2").attr('disabled', false);
			$('.inputcontent').css({"width":"100%"})
		}
	
		function oneway() {
			$('#comback').css({"display":"none"})
			$("#datepicker2").attr('disabled', true);
			$('.inputcontent').css({"width":"100%"})
		}
	}
