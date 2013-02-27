var http = require('http');
var redis = require('redis').createClient();

http.createServer(function (req, res) {
	res.writeHead(200, {'Content-Type': 'text/plain'});

	redis.zrevrange("messages", 0, 0, function (err, reply) {
		console.log("LOLCAT: "+reply);

		res.write("Last message: "+reply);
		res.end();
	});
}).listen(1337, '127.0.0.1');


console.log('Server running at http://127.0.0.1:1337/');