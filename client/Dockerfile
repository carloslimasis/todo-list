# Create image based off of the official Node 6 image
FROM node:6

# Create a directory where our app will be placed
RUN mkdir -p /opt/client

# Change directory so that our commands run inside this new dir
WORKDIR /opt/client

# Copy dependency definitions
COPY package.json /opt/client

# Install dependecies
RUN npm cache clean
RUN npm install
RUN npm install -g @angular/cli

# Get all the code needed to run the app
COPY . /opt/client

# Expose the port the app runs in
EXPOSE 4200

# Serve the app
CMD ["npm", "start"]