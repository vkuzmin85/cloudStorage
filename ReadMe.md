# Diploma thesis "Cloud storage"

## Project description
The developed service provides a REST interface for uploading files and displaying a list of files already uploaded by the user.
The service serves authorised requests. The pre-built web application (FRONT) connects to the developed service without any modifications, and uses the FRONT functionality to authorise, upload and list the user's files.

## Service functions
1. File list display
2. File upload
3. File Delete
4. Authorisation

## Description and launch of FRONT
1. Install nodejs (version 19.7.0 or later) on your computer following the instructions (https://nodejs.org/ru/download/).
2. Download FRONT(https://github.com/netology-code/jd-homeworks/blob/master/diploma/netology-diplom-frontend) (JavaScript)
3. Go to the FRONT application folder and run all commands from it.
4. Follow the README.md description of the FRONT project to launch the nodejs application (npm install, npm run serve).
5. You can specify the url to call your backend service:
    5.1. In the `.env` FRONT file (located in the root of the project) of the application, change the url to backend.
    5.2. Rebuild and run FRONT again: `npm run build`.
    5.3 The changed `url` will be saved for next runs.

## Running the client and server parts:
1. FRONT runs on port 8081 and is accessible by url in the browser http://localhost:8081
2. The docker compose up command must be executed to start the application.

## User name and password options:
1. user / 123
2. user2 / 1234

