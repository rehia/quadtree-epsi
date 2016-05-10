FROM ruby:2.2.0

ADD . /app/quadtree

WORKDIR /app/quadtree

RUN gem install bundler && bundle install

CMD ["irb", "-r",  "./main.rb"]
