<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=320, initial-scale=1"/>
	<title>jsmpeg streaming</title>
	<style type="text/css">
		body {
			background: #333;
			text-align: center;
			margin-top: 10%;
		}
		#videoCanvas {
			/* Always stretch the canvas to 640x480, regardless of its
			internal size. */
			width: 640px;
			height: 480px;
		}
	</style>
</head>
<body>
	<!-- The Canvas size specified here is the "initial" internal resolution. jsmpeg will
		change this internal resolution to whatever the source provides. The size the
		canvas is displayed on the website is dictated by the CSS style.
	-->
	<canvas id="videoCanvas" width="640" height="480">
		<p>
			Please use a browser that supports the Canvas Element, like
			<a href="http://www.google.com/chrome">Chrome</a>,
			<a href="http://www.mozilla.com/firefox/">Firefox</a>,
			<a href="http://www.apple.com/safari/">Safari</a> or Internet Explorer 10
		</p>
	</canvas>
	<script type="text/javascript" src="js/jsmpg.js"></script>
	<script type="text/javascript">
		// Setup the WebSocket connection and start the player
		//var client = new WebSocket( 'ws://10.108.79.169:8084/' );
		var client = new WebSocket('ws://192.168.1.158:8084/')
		var canvas = document.getElementById('videoCanvas');
		var player = new jsmpeg(client, {canvas:canvas});
	</script>
</body>
</html>
