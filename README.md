# Offred

#### Offred eliminates the need to construct complicated classes for simple API requests for your apps. 

# Add dependency

1. Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2. Add the dependency
```
dependencies {
	        implementation 'com.github.kshitij86:offred-android:<latest-release>'
	}
```


# Usage

Simply call Offred.giveResponse(url) and get the reponse back.
```
	 try{
         Response data = Offred.giveResponse("https://jsonplaceholder.typicode.com/todos/1");
         if(!data.isException){
                response_box.setText(data.resBody);
                time_box.setText("Took " + Double.toString(data.time) + "s");
                Toast.makeText(getBaseContext(), "Done", Toast.LENGTH_LONG).show();
            }
     } catch (Exception e){
        Log.e(getApplication().getPackageName(), e.getMessage());
        Toast.makeText(getBaseContext(), "Can't connect. Are you online?", Toast.LENGTH_LONG).show();
     }
```

**Note: Surrounding the function call in a try/catch block is necessary, as the caller throws** ```ExecutionException``` **and** ```InterruptedException``` 


