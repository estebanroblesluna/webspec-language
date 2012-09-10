        <a href="projects.htm">&larr; Back to projects</a><br><br>
        <button id="add" type="submit" class="btn btn-primary btn-mini" projectId="{projectId}">Add new</button><br><br>
        <h3>Mockups for projects</h3>
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
            <td><button type="submit" class="btn btn-mini modifyMockup" mockupId="{id}" projectId="{projectId}">Modify</button></td>
          </tr>
          {/mockups}
        </tbody>
        </table>
        