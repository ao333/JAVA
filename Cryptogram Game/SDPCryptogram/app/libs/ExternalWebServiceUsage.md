**External Web Service**
**VERSION 1.0**

This jar contains a mocked web service utility jar, with the corresponding javadoc.  An updated version and/or a live (cloud-synchronizing) version may be provided later, at the same URL.  Any updates will be noted in Piazza.  The method signatures will be kept consistent, and you should be able to substitute any updated or live jars into your project's build path without any changes to your own code.  **Do not add new methods or variables to the provided jar** - they will not be there if you change between the mocked and live jars.  Your TA may need to swap jars for testing, and will not use any alterations you make to the ExternalWebService jar.

The mock jar will not persist data between runs, and will start with a small set of sample data.  It WILL accept data updates within one run.  Do not rely on the hardcoded values (as those may change if you are using the live jar later), but this should give you examples of the relevant data and errors, as well as provide a way for you to run your app locally, without outside connectivity if necessary.


Refer to the javadoc for further explanations of the methods provided:

 - **getInstance()**  : returns static ExternalWebService
 - **addCryptogramService(java.lang.String puzzle, java.lang.String solution)  throws IllegalArgumentException** : returns java.lang.String
 - **updateRatingService(java.lang.String username, java.lang.String firstname, java.lang.String lastname, int solved, int started, int incorrect)** : returns boolean
 - **syncCryptogramService()** : returns java.util.List<java.lang.String[]>
 - **syncRatingService()** : returns java.util.List<ExternalWebService.PlayerRating>  
 - **playernameService()** : returns java.util.List<java.lang.String>  (extra function, provided for convenience)
