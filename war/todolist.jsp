<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>TO DO LIST</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
h2{
text-align:center;
background-color: #808080;
color:white;
}
.list li
{
	padding: 5px;
} 
input#checkbox {
    width: 20px;
    float:left;
    height: 42px;
}
span.displayText {
    display: inline-block;
    height: 40px;
    line-height: 47px;
    width: 200px;
    float:left;
}
span.close {
    display: inline-block;
    height: 48px;
    line-height: 47px;
    width: 40px;
    text-align: center;
    background-color: #f44336;
}
li{
	overflow: auto;
    height: 100%;
    width: 100%;
}
.hiddentext{
display: none;
float: left;
}
.userInput{
	display: none;
}
input
{
width:40%;

padding:15px;
font-size:13px;

}

body{
background-color:pink;}
.list
{
padding: 60px;
margin:0;
//background-color:#A9A9A9;
font-size:15px;
}
.close {
  float: right;
  font-size: 20px;
  font-weight: bold;
  cursor: pointer;
 
  
  
  background-color:white;
}

.close:hover {
  background-color: #f44336;
  color: white;
}
.active{
text-decoration: line-through;
}

 ul li:nth-child(odd) {
  background: #f9f9f9;
}
ul li:nth-child(even)
{
background:#D3D3D3;}

</style>
</head>
<body>

<script>
var userArray		= [];

var previousText = "";
var deleteText ="";

function guid() {
	  function s4() {
	    return Math.floor((1 + Math.random()) * 0x10000)
	      .toString(16)
	      .substring(1);
	  }
	  return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
	    s4() + '-' + s4() + s4() + s4();
}

function todolist()
{
	
		if(event.keyCode == 13)
		{
			
			var userinput= document.getElementById("todoinput");
			
			var todoObj = 
			{
				name: userinput.value,
				id: guid(),
				isCompleted: false,
				isDeleted: false
			}
			
			
			var liTag = createli(todoObj);
			
			document.getElementById("list").append(liTag);
			
			
			userArray.push(todoObj);
			userinput.value = "";

		}
    }
    /*  var form= document.getElementById("name-form");
      form.onsubmit= function(e){
	  e.preventDefault();
	  console.log(form.list.value);
};
*/

	function createli(todoObj)
     {
	var li=document.createElement("li");
	li.id = todoObj.id;
	
	
	var checkbox= document.createElement("input");
	checkbox.className="checkbox";
	checkbox.id="checkbox";
	checkbox.type = "checkbox";
	li.appendChild(checkbox);
	
	var displaytext= document.createElement("span");
	displaytext.className="displayText";
	displaytext.id="displayText";
	displaytext.innerHTML= todoObj.name;
	li.appendChild(displaytext);
	
	var hiddeninput= document.createElement("input");
	hiddeninput.className="hiddentext";
	hiddeninput.id="hiddentext";
	hiddeninput.value= displaytext.innerText;
	li.appendChild(hiddeninput);
	console.info(hiddeninput);
	
	var closebutton = document.createElement("SPAN");
	var txt = document.createTextNode("\u00D7");
	closebutton.className = "close";
	closebutton.id = "close";
	closebutton.appendChild(txt);
	li.appendChild(closebutton);
	
	
	//userArray.push(userinput);
	//console.log(userArray);
	
	
	
	displaytext.addEventListener("click", function(e){
			previousText = displaytext.innerText;
			displaytext.style.display = 'none';
			hiddeninput.style.display = 'block';
			
			
	});
		
	hiddeninput.addEventListener("keypress", function(e){
		var key = e.which || e.keyCode;
	    if (key === 13) { 
	    	displaytext.innerHTML = hiddeninput.value;
	    	
	    	var index = userArray.indexOf(previousText);
	    	
	    	if( index != -1 )
	    	{
	    		userArray[index] = hiddeninput.value;
	    	}
	    	
	    	hiddeninput.style.display = 'none';
	    	displaytext.style.display = 'block';
	    }
	});
		
	closebutton.addEventListener("click", function(e){
		var id = closebutton.parentElement.id;
		var name = closebutton.parentNode.querySelector('.displayText').className;
		
		var selectedTodo = searchTodo(id);
		
		selectedTodo.isDeleted = true;
		
		console.log(selectedTodo);
		
		closebutton.parentNode.remove();
		
		var index = searchTodoIndex(id);
		
		userArray.splice(index, 1);
		
			
	});
	checkbox.addEventListener("click", function(e){
	    var id = checkbox.parentElement.id;
	    var className = checkbox.parentNode.querySelector('.displayText').className;

	    
	    var selectedTodo = searchTodo(id);
	    
	    if( className == "displayText")
	    {
	    	selectedTodo.isCompleted = true;
	    	
	    } else {
	    	selectedTodo.isCompleted = false;

	    }
	    
	    console.log(selectedTodo);
		checkbox.parentElement.querySelector('.displayText').classList.toggle("active");
	});

	  return li;
	
   
}

/*function myFunction(item) {
    console.log(item.id + " " +item.name ); 
    var arr = [];
    for(var i=1; i<=item.length; i++) {
       arr.push(item.name);
    }
}*/



function searchTodo(id)
{
	for( var i=0; i<userArray.length; i++)
	{
		if( userArray[i].id == id ){
			
			return userArray[i];
		}
	}
}

function searchTodoIndex(id)
{
	for( var i=0; i<userArray.length; i++)
	{
		if( userArray[i].id == id ){
			
			return i;
		}
	}
}
function saveForm(email)
{
	 	var myForm = document.createElement('form');
	    myForm.setAttribute('action','/ListpageServlet');
	    myForm.setAttribute('method', 'post');
	    myForm.setAttribute('hidden', 'true');
	    
	    //var array = userArray.forEach(myFunction);
	    //console.log(array);
	    
	   var s = document.createElement("input"); 
			s.setAttribute('type',"texbox");
			s.setAttribute('name',"listform");
	  var nameCollection = [];
		 for(var i=0; i<userArray.length; i++)
		{
			nameCollection.push(userArray[i].name);
		} 
		 
		   s.setAttribute('value',nameCollection);
	  var s1 = document.createElement("input"); 
			s1.setAttribute('type',"texbox");
			s1.setAttribute('name',"email");
		    s1.setAttribute('value',email); //new added 
		 myForm.appendChild(s);
		 myForm.appendChild(s1);
		
		document.body.appendChild(myForm);
		myForm.submit();
}


$(document).ready(function(){
	$.ajax({
		url : '/retrievelistservlet',
		type : 'GET',
		success: function(data)
		{
			console.log(data);
			var jsdata = JSON.parse(data);
			
			for (i = 0; i < jsdata.length; i++) {
				obj = jsdata[i];
				console.log(obj.list,obj.CustomerEmail);
				var partsOfStr = (obj.list).split(',');
				for(i=0;i<partsOfStr.length;i++){
					console.log("Lising it::: "+ partsOfStr[i]);
					var todoObj = 
					{
						//name:partsOfStr,
						name:partsOfStr[i],
						id: guid(),
						isCompleted: false,
						isDeleted: false
					}
					var liTag = createli(todoObj);
					document.getElementById("list").append(liTag);
					
					
					userArray.push(todoObj);
				}
				console.log("Lising it::: "+ partsOfStr);
				
			}
			
		}
		
		
	});
});

	</script>
<div id="mydiv" class="list">
  <h2 style="margin:10px" id="heading">To Do List</h2>
  <input type="text" id="todoinput" name="list" placeholder="Enter the new list here" onkeypress="todolist();"/>
  <ul id="list"> 
  <%String list= (String)request.getAttribute("list");%>
  </ul>

 
  <input type="button" name="save" value="save" onclick="saveForm('email');"/>
  
</div>

</body>
</html>