var hashiApp = angular.module('hashiApp') // gets it

hashiApp.service('fileUploaderFactory', function(GeoCoder) {


	var files = [];
	var fileZero= null;
	var fileOne= null;
	var fileTwo= null;
	var fileThree= null;
	var fileFour= null;
	var fileFive= null;
	var fileSix= null;



    //Initialize files
	this.initializeFileUploader = function (){
	    files = [];
		fileZero= null;
		fileOne= null;
		fileTwo= null;
		fileThree= null;
		fileFour= null;
		fileFive= null;
		fileSix= null;
	}


	//file handler method

	this.fileUploadedSuccessfully  = function(file, index)	{
		switch(index) {
		case 0:
			fileZero = file;
			break;
		case 1:
			fileOne = file;
			break;
		case 2:
			fileTwo = file;
			break;
		case 3:
			fileThree = file;
			break;
		case 4:
			fileFour = file;
			break;
		case 5:
			fileFive = file;
			break;
		case 6:
			fileSix = file;
			break;
		default:
			console.log('There should not be a default failover at this thing.');
		}
		return;
	};

	this.removeFile  = function (index){
		switch(index) {
		case 0:
			fileZero = null;
			break;
		case 1:
			fileOne = null;
			break;
		case 2:
			fileTwo = null;
			break;
		case 3:
			fileThree = null;
			break;
		case 4:
			fileFour = null;
			break;
		case 5:
			fileFive = null;
			break;
		case 6:
			fileSix = null;
			break;
		default:
			console.log('There should not be a default failover at this thing.');
		}
	}
	
	

	this.getFiles  = function (){
		files = [];
		var counter=0;
		if(fileZero){
			var temp={file: fileZero, imageIndex:0};
			files.push(temp);
		}
		if(fileOne){
			var temp={file: fileOne, imageIndex:1};
			counter += 1;
			files.push(temp);

		}
		if(fileTwo){
			counter += 1;
			var temp={file: fileTwo, imageIndex:2};
			files.push(temp);
		}if(fileThree){
			counter += 1;
			var temp={file: fileThree, imageIndex:3};
			files.push(temp);

		}if(fileFour){
			counter += 1;
			var temp={file: fileFour, imageIndex:4};
			files.push(temp);

		}if(fileFive){
			counter += 1;
			var temp={file: fileFive, imageIndex:5};
			files.push(temp );

		}if(fileSix){
			counter += 1;
			var temp={file: fileSix, imageIndex:6};
			files.push(temp);
		}
		return files;
	}

});