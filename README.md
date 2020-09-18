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
The example application shows usages in an actual app environment.

# Usage

### _GET_ requests

    Offred.get(url)
    API calls are made on a separate background thread and a Response object is returned.

```
	try{
        Offred offred = new Offred();
        Future<Response> fr =  offred.get("https://jsonplaceholder.typicode.com/todos/1");
        Response data = fr.get();
        if(!data.isException && data.resBody != "NULL_REQUEST"){
            Log.d(TAG, "onClick: Call to web service successful");
            }
        } catch (Exception e){
            Log.e(TAG, e.getMessage());
        }
```

### _POST_ requests
    Offred.post(url, postData)
```
    try{
            ...
            // JSON to POST to RESTful API
            String passJSON = "{\"name\":" + name.getText() + ",\"salary\":" + salary.getText() + ",\"age\":"+ age.getText() +"}";
            
            Offred offred = new Offred();
            Future<Response> fr = offred.post("https://dummy.restapiexample.com/api/v1/create", passJSON);
            Response data = fr.get();
            ...
       } catch (Exception e){
            Log.e(TAG, e.getMessage());
        }
```

### _DELETE_ requests
    Offred.delete(url)


### `RESPONSE` object
Performing a HTTP request returns a `Response` object, which contains response body along with response headers, total request time etc. 
Below is a full list.

| ```Response data```|```Type```|
|---------------|-------------|
| ```time```    |  ```double```    |
| ```resBody```|  ```JSONObject```|
| ```statusCode```| ```int```   |
| ```headers```   | ```Map<String, List<String>>``` |
| ```isException```   | ```Boolean``` |


# Future Scope & TODOs
1. Better `Exception` handling
2. Extend HTTP requests
3. Caching support
4. Support for XML data

# Demo App
The app included with repo showcases use cases for Offred.
[Demo app](ss.jpeg)
