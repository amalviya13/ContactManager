function createTable(){
	console.log("get");
	var arrContacts = new Array();
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8080/crm/webapi/contacts',
		dataType: 'json',
		contentType: 'application/json',
		success: function(data){
			console.log("worked");
			var rows = "";
			$.each(data, function(){
				rows += "<tr><td> <a href='contactDetails.html?id=" + this.id + "'>" + this.firstName + " " + this.lastName +"</a></td></tr>";
			});
			$(rows).appendTo("#contacts tbody")
		},
		error: function(xhr){
			console.log("error: " + xhr.status);
		}
	})
}
function createContactTable(){
	var id = getURLVariables()["id"];
	console.log(id);
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8080/crm/webapi/contacts/' + id,
		dataType: 'json',
		contentType: 'application/json',
		success: function(data){
			var row = "";
			row += "<tr><td>" + data.firstName + "<td>" + data.lastName + "<td>" + data.phone + "</td></tr>";
			console.log(data);
			$(row).appendTo("#contactDetails tbody")
		},
		error: function(xhr){
			console.log("error: " + xhr.status);
		}
	})
}


function getURLVariables() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}


function editContact(){
	var id = getURLVariables()["id"]
	window.location.replace('editContact.html?id=' + id);
}

function cancelButton(){
	var id = getURLVariables()["id"]
	window.location.replace('contactDetails.html?id=' + id);
}

function showContactsButton(){
	window.location.replace('test.html');
}

function submitedEditForm(){
	var firstName = document.editContactForm.firstNameID.value;
	var lastName = document.editContactForm.lastNameID.value;
	var phone = document.editContactForm.phoneID.value;
	var id = getURLVariables()["id"];
	var dataMap = {};
	dataMap["firstName"] = firstName;
	dataMap["lastName"] = lastName;
	dataMap["phone"] = phone;
	$.ajax({
		type: 'PUT',
		url: 'http://localhost:8080/crm/webapi/contacts/' + id,
		dataType: 'json',
		data : JSON.stringify(dataMap),
		contentType: 'application/json',
		success: function(){
		},
		error: function(xhr){
		}
	})
	window.location.replace('contactDetails.html?id=' + id);
}

function addContact(){
	window.location.replace('addContact.html');
}

function deleteContact(){
	var id = getURLVariables()["id"];
	console.log(id);
	$.ajax({
		type: 'DELETE',
		url: 'http://localhost:8080/crm/webapi/contacts/' + id,
		dataType: 'json',
		contentType: 'application/json',
		success: function(){
		},
		error: function(xhr){
		}
	})
	goHome();
}

function goHome(){
	window.location.replace('test.html')
}

function submitNewContact(){
	var firstName = document.addContactForm.firstNameID.value;
	var lastName = document.addContactForm.lastNameID.value;
	var phone = document.addContactForm.phoneID.value;
	var id = getURLVariables()["id"];
	var dataMap = {};
	dataMap["firstName"] = firstName;
	dataMap["lastName"] = lastName;
	dataMap["phone"] = phone;
	$.ajax({
		type: 'POST',
		url: 'http://localhost:8080/crm/webapi/contacts/',
		dataType: 'json',
		data : JSON.stringify(dataMap),
		contentType: 'application/json',
		success: function(){
		},
		error: function(xhr){
			alert(xhr);
		}
	})
	window.location.replace('test.html');

}

function submitNewUser(){
	var firstName = document.addUserForm.firstNameID.value;
	var lastName = document.addUserForm.lastNameID.value;
	var email = document.addUserForm.emailID.value;
	var id = getURLVariables()["id"];
	var password = document.addUserForm.passwordID.value;
	var dataMap = {};
	dataMap["firstName"] = firstName;
	dataMap["lastName"] = lastName;
	dataMap["email"] = email;
	dataMap["password"] = password;
	$.ajax({
		type: 'POST',
		url: 'http://localhost:8080/crm/webapi/users/',
		dataType: 'json',
		data : JSON.stringify(dataMap),
		contentType: 'application/json',
		success: function(){
		},
		error: function(xhr){
			alert(xhr);
		}
	})
	window.location.replace('test.html');
}

function autoFillContact(){
	var id = getURLVariables()["id"]
	var editFirstName;
	var editLastName;
	var editPhone;
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8080/crm/webapi/contacts/' + id,
		dataType: 'json',
		contentType: 'application/json',
		success: function(data){
			editFirstName = data.firstName;
			editLastName = data.lastName;
			editPhone = data.phone;
			console.log(editFirstName);
			document.editContactForm.firstNameID.value = editFirstName;
			document.editContactForm.lastNameID.value = editLastName;
			document.editContactForm.phoneID.value = editPhone;
		},
		error: function(xhr){
			console.log("error: " + xhr.status);
		}
	})
}

function addUser(){
	window.location.replace('userSignup.html');
}