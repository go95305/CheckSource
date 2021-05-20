FROM nginx

RUN mkdir /app

WORKDIR /app

RUN mkdir ./dist

ADD ./dist ./dist

RUN rm /etc/nginx/conf.d/default.conf

COPY ./nginx.conf /etc/nginx/conf.d

EXPOSE 80

CMD ["nginx","-g","daemon off;"]

