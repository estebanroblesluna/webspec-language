<div class="mainContainer">
    <div id="photos">
        $scenarios:{ scenario | 
        <div class="scenario">
       		<div class="scenarioDescription">$scenario.description$</div>
                <span class="photo">
                    <a href="#$scenario.id$">
                        <img src="$scenario.diagramFilePath$" alt="$scenario.description$" width="900"/>
                    </a>
                </span>
                <div id="$scenario.id$">
                    <img src="$scenario.diagramFilePath$"/>
                </div>
            <div class="stepByStep">$stepByStep$</div>
            <table class="scenarioTable">
                $scenario.scenarioSteps: {scenarioStep | 
                    <tr>
                        <td class="scenarioTd">
                            <span class="photo">
                                <a href="#$scenarioStep.interactionORnavigationCroppedImageId$">
                                    <img width="250" style="cursor: pointer; opacity: 1;" alt="$scenarioStep.name$" src="$scenarioStep.interactionORnavigationCroppedImagePath$"/>
                                </a>
                            </span>
                            <div id="$scenarioStep.interactionORnavigationCroppedImageId$">
                                <img src="$scenarioStep.interactionORnavigationCroppedImagePath$"/>
                            </div>
                        </td>

                        <td class="scenarioTd explanationScenarioTd"><div class="explanationTdContainer">$scenarioStep.explanation$</div></td>
                        
                        <td class="scenarioTd">
                            <span class="photo">
                                <a href="#$scenarioStep.mockupImageId$">
                                    <img width="200" style="cursor: pointer; opacity: 1;" alt="$scenarioStep.name$" src="$scenarioStep.mockupImagePath$"/>
                                </a>
                            </span>
                            <div id="$scenarioStep.mockupImageId$">
                                <img src="$scenarioStep.mockupImagePath$"/>
                            </div>
                        </td>
                    </tr>
                }$
            </table>
            <div class="scenarioSeparator"/>
            <br>
        </div>
        }$
    </div>
</div>