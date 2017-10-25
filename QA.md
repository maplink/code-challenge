# QA code challenge ![alt Batlogo](public/images/batlogo-small.png)

Batman needs your help! A supervillain was found.

The interface created for Batdevices should show information about the possible attacks in Gotham City, but it is looking buggy...

In this interface, Batman must be able to tell the Batcave's supercomputer the location (coordinate) of the recently found supervillain to identify which locations are most likely to be attacked next time.

You are requested to create acceptance tests scenarios to validate if coordinates sent by batmobile GPS are returning precise information about villains within the [Gotham boundaries](https://gist.githubusercontent.com/pitteri/d56780d610cb8e0a43bfa94fc54b71cd/raw/dcdd965c84cd05d856ae32646be69868d4a80afa/gotham_bbox.json). You can define the coordinates to best fit your defined test scenarios.

Possible Automated tests tools
* [Rest Assured](http://rest-assured.io/) - [more info...](http://www.baeldung.com/rest-assured-tutorial)
* [Cucumber for Java](https://cucumber.io/) - [more info...](https://www.youtube.com/watch?v=pD4B839qfos&list=PL_noPv5wmuO_t6yYbPfjwhJFOOcio89tI) 

Remember, the requirements issued for the:
[Back-end challenge](BACKEND.md)

[Front-end challenge](FRONTEND.md)

Batman is a very demanding client, especially with the quality of his devices and the software they run. Bugs found are welcome. Confidence in his tools is a requirement. 
Surprise him!

### Batcave Supercomputer API

**Input**

Villain coordinate: http://code-challenge.maplink.com.br/coordinate?q={latitude},{longitude}

**Output**

[Sample](https://gist.githubusercontent.com/pitteri/578a6801d6f504eda6f6ce84cad59f89/raw)  
