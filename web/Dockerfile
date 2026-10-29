# FROM supplies the official lightweight nginx static web server.
FROM nginx:stable-alpine
# COPY installs the reverse proxy configuration.
COPY default.conf /etc/nginx/conf.d/default.conf
# COPY installs the calculator page.
COPY index.html /usr/share/nginx/html/index.html
# EXPOSE documents the web service port.
EXPOSE 80
# CMD keeps nginx running as the foreground container process.
CMD ["nginx", "-g", "daemon off;"]
