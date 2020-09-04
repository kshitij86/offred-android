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

### _GET_ requests

    ```Offred.get(url)``` <br>
    API calls are made on a separate background thread and a Response object is returned.

```
	try{
	   Offred offred = new Offred();
           Response data = offred.get("https://jsonplaceholder.typicode.com/todos/1");
           if(!data.isException && data.resBody != "NULL_REQUEST"){
            	Log.d(TAG, "onClick: Call to web service successful");
                response_box.setText(data.resBody);
                time_box.setText("Took " + Double.toString(data.time) + "s");
                Toast.makeText(getBaseContext(), "Done", Toast.LENGTH_LONG).show();
               }
	} catch (Exception e){
	    Log.e(TAG, e.getMessage());
        }
```

### _POST_ requests
    ```Offred.post(url, postData)```<br>
  
```
  if(!name.getText().equals("") && !salary.getText().equals("") && !age.getText().equals("")){
      String data = "{\"name\":" + name.getText() + ",\"salary\":" + salary.getText() + ",\"age\":"+ age.getText() +"}";
      Offred offred = new Offred();
      Response response = offred.post("https://dummy.restapiexample.com/api/v1/create", data);
      Toast.makeText(this, response.resBody, Toast.LENGTH_SHORT).show();
  } else {
      Toast.makeText(this, "Empty values not allowed !", Toast.LENGTH_SHORT).show();
  
```
