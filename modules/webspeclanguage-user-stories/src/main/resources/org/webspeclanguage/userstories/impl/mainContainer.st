<div class="mainContainer">
        $scenarios:{ scenario | 
        <div class="scenario">
       		<div class="scenarioDescription">$scenario.description$</div>
            <div id="demo" class="scenarioDiagram">
                <img class="fancyzoom" src="$scenario.diagramFilePath$" alt="$scenario.description$" width="900"/>
            </div>
            <div class="stepByStep">$stepByStep$</div>
            <table class="scenarioTable">
                $scenario.scenarioSteps: {scenarioStep | 
                    <tr>
                        <td class="scenarioTd"><div id="demo"><img width="250" class="fancyzoom" style="cursor: pointer; opacity: 1;" alt="$scenarioStep.name$" src="$scenarioStep.interactionORnavigationCroppedImagePath$"/></div></td>
                        <td class="scenarioTd explanationScenarioTd"><div class="explanationTdContainer">$scenarioStep.explanation$</div></td>
                        <td class="scenarioTd"><div id="demo"><img width="200" class="fancyzoom" style="cursor: pointer; opacity: 1;" alt="$scenarioStep.name$" src="$scenarioStep.mockupImagePath$"/></div></td>
                    </tr>
                }$
            </table>
            <div class="scenarioSeparator"/>
            <br>
        </div>
        }$
</div>