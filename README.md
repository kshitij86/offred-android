# Offred

#### Offred eliminates the need to construct complicated classes for simple GET requests using OkHttp. 

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
		// Example request 
		String response = Offred.giveResponse("https://jsonplaceholder.typicode.com/todos/1");
		// Do something with reponse
	} catch (Exception e){
              Toast.makeText(getBaseContext(), "FATAL ERROR", Toast.LENGTH_LONG).show();
        }
```

**Note: Surrounding the function call in a try/catch block is necessary, as the caller throws** ```ExecutionException``` **and** ```InterruptedException``` 


