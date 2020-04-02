window.app = new Vue({
    el: '#app',
    data: {
    	errorText: "",
        page: "create",
        username:"",
        selectedUser:{
        	userName :"",
        	email:"-",
        	active:false,
        	id:null,
        },
        updatedUser:{
        	userName :"",
        	email:"-",
        	active:false,
        	id:null,
        	role:"",
        },
        selected: '0',
        deleted:'0',
        updated:'0',
        options: [{
        	userName: "",
            email:"", 
            id:"",
        }],
        // createUserData.username
        createUserData: {
        	userName: "",
            password: "",
            email:"",
            role:""
        },
        
    },
    methods: {
    	setError: function(response) {
    		console.log(response);
            if(response.status==='success') return;
            if(response.status=='403'){
            	this.errorText="Permission Denied";
            	return;
            } 
            response.text().then(function(data){
            	console.log(data);
        		this.errorText=data;
        	})
            if(response.status==='500'){
            	console.log("adsf");
            	this.errorText = "Error: " + "Action not allowed";
            	let self = this;
            	setTimeout(() => {self.errorText = '';}, 10000);
            }
        },
    	refeshUserBase: function(){
    		fetch('/all-user').then(this.getAllUser);
    	},
    	updateUser: function(){
    		console.log("inside update user");
    		fetch('/update-user', {
                
                // Adding method type
                method: "PUT", 
                body: JSON.stringify({ 
                    id:this.updatedUser.id,
                	userName: this.updatedUser.userName, 
                    email: this.updatedUser.email, 
                    password: this.updatedUser.password,
                    role: this.updatedUser.role,
                    isActive: this.updatedUser.active
                }), 
                  
                headers: { 
                    "Content-type": "application/json; charset=UTF-8"
                }
              
            }).then(this.setError);

    	},
    	createUser: function () {
    		console.log("inside create user");
      
            
            fetch('/create-user', {
                
                // Adding method type
                method: "POST", 
                body: JSON.stringify({ 
                    userName: this.createUserData.userName, 
                    email: this.createUserData.email, 
                    password: this.createUserData.password,
                    role:this.createUserData.role,
                }), 
                  
                headers: { 
                    "Content-type": "application/json; charset=UTF-8"
                }
              
            }).then(function(response) {
                console.log("jbkjb");
            	if (!response.ok) {
                    throw Error(response.statusText);
                }
            })
           
    	},
        getAllUser: function(response){
        	let appdata=this;
        	response.json().then(function(data){
        		console.log(data);
        		appdata.options=data;
        	})
        
        },
        performDelete:function(){
        	fetch('/delete-user?' +  new URLSearchParams({
        		id:this.deleted}), {method: "DELETE"}).then(this.setError);
        	fetch('/all-user').then(this.getAllUser);
        	deleted='0';
        	

        },
        performUpdate:function(){
        	fetch('/getUserByID?' +  new URLSearchParams({
        		id:this.updated
        		})).then(response => response.json())
                .then(updatedUser => {
                    this.updatedUser = updatedUser;
                });
        },
        changeGetItem:function(){
        	this.getUserDetail();
        	
        },
        changeUpdateItem:function(){
        	this.performUpdate();
        },
        changeDeleteItem:function(){
        	this.performDelete();
        },
        getUserDetail: function(){
        	fetch('/getUserByID?' +  new URLSearchParams({
        		id:this.selected})).then(response => response.json())
                .then(selectedUser => {
                    this.selectedUser = selectedUser;
                });
        },
        getCurrentUser: function(response){
    		let appdata=this;
			response.json().then(function(data){
	    	appdata.username=data.username;

			})
		}
    	
    },
    mounted: function () {
    	fetch('/userDetails').then(this.getCurrentUser);
        fetch('/all-user').then(this.getAllUser);
    }
});
