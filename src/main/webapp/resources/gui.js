$(document).ready(function() {

	var timerHandler = setInterval(getMessages, 2000);

	$('body').keypress(function(event) {
		if (event.keyCode == '13') {
			postMessage();
		}
	});

	$(window).unload(function() {
		clearInterval(timerHandler);
	});

	function postMessage() {
		var message = new Object();
		message.username = $('#username').val();
		message.contents = $('#contents').val();
		var jsonMessage = JSON.stringify(message);

		$.ajax({
			url: "/message",
			type : "POST",
			contentType : 'application/json',
			data: jsonMessage,
			error : function(data) {
				alert('Arglebargle, glop-glyf? There be dragons here. Data: ' + data);
			}
		});
	}

	function getMessages() {
		$.getJSON("/message", function(messages) {
			var html = "";
			$.each(messages, function(i, message) {
				var t = message;
				html += "<li>" + message.timestring + " <span class='user'>" + message.username + "</span>: <span class='message'>" + message.contents + "</span></li>";
			});
			$("#messages").html(html);
		});
	}
});

