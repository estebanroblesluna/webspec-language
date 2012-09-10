        <a href="sprints.htm?projectId={projectId}">&larr; Back to sprints</a><br><br>
        <button id="add" type="submit" class="btn btn-primary btn-mini" sprintId="{sprintId}">Add new</button>
        <h3>Mockups for sprint</h3>
        <table class="table table-condensed">
        <thead>
          <tr>
            <th>Name</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {#mockups}
          <tr>
            <td>{title}</td>
            <td><button type="submit" class="btn btn-mini modifyMockup" mockupId="{id}" sprintId="{sprintId}">Modify</button></td>
          </tr>
          {/mockups}
        </tbody>
        </table>
        