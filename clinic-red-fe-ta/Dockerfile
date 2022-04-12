FROM node:14.17.0-alpine as build-step
WORKDIR /clinicApp
COPY . /clinicApp
RUN npm install
RUN npm run build --prod

FROM nginx:1.20.1
COPY --from=build-step /clinicApp/dist/clinic-red-fe-ta /usr/share/nginx/html
EXPOSE 42002:80
