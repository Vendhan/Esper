# Esper
Esper is an app fecthes mobiles and its related deatils from api and shows it to user to make valid combinations according to exclusions given

Major Libraries/ Components Involved:

1. It uses retrofit and okhttp for network connection to fetch data from api
2. it uses Room DB to store data to support offline
3. App follows MVVM architecture
4. It uses Koin for dependency injection

App's functionality:

1. When user opens app it will try to make network call to fetch data from api and it will store the same in DB in desired manner
2. As soon as api fetch and DB insertion successful, app will fetch data from DB to show in UI
3. UI is filled with chips to provide option to user to make valid combinations
4. For each selection of chip, App will check in Exlcusion table for any exclusions for the corresponsing featureID/optionID involved or not
5. If there is no exclusions found, app will not make any difference, if there is any exclusions, app will unselect/disable those chips which are not valid
6. Submit button will take user to summary screen after selections are made from each feature sections
7. Summary screen will show mobile details along with the addiotnal features/valid combinations made by the user
