<head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />

	 $cssFiles:{ cssFile | 
		<link rel="stylesheet" href="$cssFile$" type="text/css" />
	 }$

	 $jsFiles:{ jsFile | 
		<script type="text/javascript" src="$jsFile$"></script>
	 }$	
	
	<script type="text/javascript" charset="utf-8">
		jQuery(document).ready(function() {
			jQuery('span.photo a').fancyZoom({scaleImg: true, closeOnClick: true, directory: 

			'$imageDirectory$'

			});
			jQuery('#medium_box_link').fancyZoom({width:400, height:300});
			jQuery('#large_box_link').fancyZoom();
			jQuery('#flash_box_link').fancyZoom();
		});
	</script>
	
	<style type="text/css" media="screen">
		#large_box {width:800; height:600;}
	</style>

</head>
