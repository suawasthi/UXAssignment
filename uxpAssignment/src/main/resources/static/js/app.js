window.app = new Vue({
    el: '#app',
    data: {
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
        },
        selected: '0',
        deleted:'0',
        updated:'0',
        options: [{
        	username1: "",
            email:"", 
            ID:"",
        }],
        createUserData: {
        	username1: "",
            password: "",
            email:"", 
            role:"",
        },
        
    },
    methods: {
    	refeshUserBase: function(){
    		fetch('/all-user').then(this.getAllUser);
    	},
    	createUser: function (gameMode) {
            fetch('/create-user?' + new URLSearchParams({
                username1: this.createUserData.username1,
                password: this.createUserData.password,
                email: this.createUserData.email,
                role:this.createUserData.role,
            }));
            fetch('/all-user').then(this.getAllUser);
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
        		id:this.deleted}));
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
