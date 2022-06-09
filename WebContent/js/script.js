/**
 *
 */

/** 初期状態に戻す */
function formReset(){
	let lastName = document.getElementById('lastName');
	let firstName = document.getElementById('firstName');
	let year = document.getElementById('year');
	let month = document.getElementById('month');
	let day = document.getElementById('day');
	let job = document.getElementById('job');
	let phoneNumber = document.getElementById('phoneNumber');
	let mailAddress = document.getElementById('mailAddress');

	lastName.value = '';
	firstName.value = '';
	year.value = '';
	//job.value = '';
	phoneNumber.value = '';
	mailAddress.value = '';

	for (const element of document.getElementsByName('sex')) {
    element.checked = false;
	}

	month.options[0].selected = true;
	day.options[0].selected = true;
	job.options[0].selected = true;
}

/** memberIdのvalueを取得し、memberIdHiddenのvalueに格納する */
function updateValue(){
	const memberIdHidden = document.getElementById('memberIdHidden');
	const memberId = document.getElementById('memberId');
	memberIdHidden.value = memberId.value;
}