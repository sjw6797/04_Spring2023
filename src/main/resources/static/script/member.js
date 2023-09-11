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
		$('#comback').css({ "display": "block" })
		$('.inputcontent').css({ "width": "16.3%" })

	}

	function oneway() {
		$('#comback').css({ "display": "none" })
		$('.inputcontent').css({ "width": "19.6%" })
	}
	
	function select() {
		var url = "?"
		var name = "?"
		 var option = "fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no, width=500, height=500";
		
		window.open(url, name,  option);
	}

} else {
	function roundtrip() {
		$('#comback').css({ "display": "block" })
		$("#datepicker2").attr('disabled', false);
		$('.inputcontent').css({ "width": "100%" })
	}

	function oneway() {
		$('#comback').css({ "display": "none" })
		$("#datepicker2").attr('disabled', true);
		$('.inputcontent').css({ "width": "100%" })
	}
}

// 07/06 신정우 작성 07/11 내용추가(두원)
	function submit() {
	if (document.formm.depAirportNm.value == "") {
		alert("출발지를 입력하세요")
		return false
	} else if (document.formm.arrAirportNm.value == "") {
		alert("도착지를 입력하세요")
		return false
	} else if (document.formm.passenNum.value == null || document.formm.passenNum.value <= 0) {
		alert("탑승인원을 입력하세요")
		return false
	} else {
		document.formm.submit()
	}
}

function memberDelete() {
	var userInput = confirm("정말 탈퇴 하시겠습니까?")
	if(userInput == true) {
		location.href = "memberDelete"
	} else {
		return;
	}

}





