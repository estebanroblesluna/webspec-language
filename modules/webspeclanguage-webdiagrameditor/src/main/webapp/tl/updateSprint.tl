      <form class="well form-horizontal">
        <fieldset>
        <div class="control-group">
          <label class="control-label">Title:</label>&nbsp;&nbsp;
          <input id="title" type="text" value="{title}">
        </div>
          
        <div class="control-group">        
          <label class="control-label">Initial date (dd/mm/yyyy):</label>&nbsp;&nbsp;
          <input id="initDate" type="text" value="{initDate}"> 
        </div>
        <div class="control-group">         
          <label class="control-label">End date (dd/mm/yyyy):</label>&nbsp;&nbsp;
          <input id="endDate" type="text" value="{endDate}">
        </div>
        <input type="hidden" id="sprintId" name="sprintId" value="{id}">
        <div class="form-actions">
        <button id="saveButton" class="btn btn-primary">Save</button>
        
        </div>
        </fieldset>
      </form>