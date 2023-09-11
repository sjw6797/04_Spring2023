function selectimg(){
	var opt ="toolbar=no,menubar=no,resizable=no,width=450,height=200";
	window.open('selectimg','selecting',opt);
}

function selectedimage(){
	document.frm.submit();
	
}

function passCheck( qna_num){
	var url="passCheck?qna_num="+qna_num;
	var opt = "toolbar=no, menubar=no, resizable=no, width=500, height=250,scrollbars=no";
	window.open(url,"passCheck",opt);
}

function enabled(){
	if(document.frm.check.checked==true){	 // 체크박스가 체크해제되었을때 눌러서 체크해줬다면
		document.frm.pass.disabled=false;	// disable옵션을 끈다
	}else{									// 체크박스에 체크가 되어있을때 눌러서 체크해제시켜줬따면
		document.frm.pass.disabled=true;
		document.fom.pass.value="";	// boxcheck해제일땐 value값도 지워줌
	}
}