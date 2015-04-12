function addUser(){

var email = document.getElementById("email").value;
var username = document.getElementById("username").value;
var password1 = document.getElementById("password1").value;
var password2 = document.getElementById("password2").value;

var result_login;

  if (username == "" || password1 == "" || email == "" || password2 == ""){
        alert("Please enter username, password, and email.");
        return;
  }

  if (password1 != password2){
        alert("Passwords do not match. Please re-enter"); 
        return; 
  }

  if (window.XMLHttpRequest){
      xmlhttp = new XMLHttpRequest();
  }
  else {
          xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
  }
 
   xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState ==4 && xmlhttp.status ==200){
 	  result_login= xmlhttp.responseText;
          result_login = result_login.trim();
       }

   }
    
  xmlhttp.open("GET","../Forum/resources/php/newUserCheck.php?email="+email+"&username="+username+"&password1="+password1,false); 
  xmlhttp.send();    

console.log(result_login); 
if (result_login!="bad"){
document.newUserForm.action="forum.php";
document.getElementById("hiddenfield").value = result_login;
document.newUserForm.submit(); 
  }
else 
alert("Username already in use.");


};