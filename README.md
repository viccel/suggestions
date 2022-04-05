# Suggestions
Suggestions API

**Near match**

    GET https://apicities.azurewebsites.net/suggestions/?q=Londo&latitude=43.70011&longitude=-79.4163

```json
{
  "suggestions": [
    {
      "name": "London, KY, US",
      "latitude": 37.12898,
      "longitude": -84.08326,
      "score": 0.9,
      "distance": 14815.380264463305
    },
    {
      "name": "Londontowne, MD, US",
      "latitude": 38.93345,
      "longitude": -76.54941,
      "score": 0.9,
      "distance": 14873.619289034832
    },
    {
      "name": "New London, CT, US",
      "latitude": 41.35565,
      "longitude": -72.09952,
      "score": 0.8,
      "distance": 15050.322611309091
    },
    {
      "name": "London, OH, US",
      "latitude": 39.88645,
      "longitude": -83.44825,
      "score": 0.7,
      "distance": 15104.610267039658
    },
    {
      "name": "Londonderry, NH, US",
      "latitude": 42.86509,
      "longitude": -71.37395,
      "score": 0.7,
      "distance": 15199.24970819075
    },
    {
      "name": "London, 08, CA",
      "latitude": 42.98339,
      "longitude": -81.23304,
      "score": 0.6,
      "distance": 15400.817028226478
    },
    {
      "name": "New London, WI, US",
      "latitude": 44.39276,
      "longitude": -88.73983,
      "score": 0.6,
      "distance": 15687.49897734706
    }
  ]
}
```

**No match**

    GET https://apicities.azurewebsites.net/suggestions/?q=NingunoPuemeMarch

```json
{
  "suggestions": []
}
```

## Deliverable
- Add a README with a *curl* example on how to consume your service (must be the address of the service deployed)
- You need to upload your code to a public hosting repository site (GitHub, Gitlab, Bitbucket, etc)
- When your done, share with us the link of the us
