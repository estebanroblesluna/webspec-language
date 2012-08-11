<head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />

        <title>User stories</title>		

        <!-- required -->
        <script type="text/javascript" src="jquery.js"></script>
        <!-- optional -->
        <script type="text/javascript" src="jquery.shadow.js"></script>
        <script type="text/javascript" src="jquery.ifixpng.js"></script>

        <script type="text/javascript" src="jquery.fancyzoom.min.js"></script>

        <script type="text/javascript">
                jQuery(function(){
                        jQuery('#demo > a:first').fancyzoom({Speed:400,showoverlay:false});
                        jQuery('#demo > a:last').fancyzoom({Speed:400,showoverlay:false});
                        jQuery('#nooverlay').fancyzoom({Speed:400,showoverlay:false});
                        jQuery('img.fancyzoom').fancyzoom();
                });
        </script>
        <style type="text/css">
                A IMG {
                	border:0;
                }
                A{
                	text-decoration:none;color:#000;
                }
                body {
                    background-color:#F5F5F5;
                    font-family: "Verdana", sans-serif;
                    color: #666;
                }
				.scenarioTable, .scenarioTd {
					border: 2px solid gray;
				}
                .title {
                    text-align:center;
                    font-size:45px;
                    padding-top:40px;
                    padding-bottom:50px;
                }
                .scenarioDescription {
                	text-decoration:underline;
                	font-size:25px;
                	word-wrap:break-word;
                	margin-left:120px;
                	margin-right:120px;
                }
                .scenarioDiagram {
                	padding-top:30px;
                    padding-bottom:20px;
                }
                .scenarioSeparator {
                	margin-top:50px;
                	margin-bottom:50px;
                }
                .stepByStep{
                	padding-top:30px;
                    padding-bottom:10px;
                    font-size:20px;
                }
                .explanationScenarioTd {
                	width:500px;
                }
                .explanationTdContainer {
					padding: 20px 20px 20px 20px;
                }
        </style>
</head>
