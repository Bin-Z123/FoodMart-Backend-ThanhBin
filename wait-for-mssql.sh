#!/bin/sh

HOST=$1
PORT=$2
shift 2

echo "⏳ Waiting for SQL Server at $HOST:$PORT..."

while ! nc -z $HOST $PORT; do
  sleep 1
done

echo "✅ SQL Server is up. Starting app..."

exec "$@"