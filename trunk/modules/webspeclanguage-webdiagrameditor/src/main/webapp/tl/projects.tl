        <button id="add" type="submit" class="btn btn-primary btn-mini">Add new</button><br><br>
        <h3>Projects</h3>
        <table class="table table-condensed">
        <thead>
          <tr>
            <th>Name</th>
            <th></th>
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {#projects}
          <tr>
            <td>{name}</td>
            <td><button type="submit" class="btn btn-mini modifyProject" projectId="{id}">Modify</button></td>
            <td><button type="submit" class="btn btn-mini getSprint" projectId="{id}">Sprints</button></td>
            <td><button type="submit" class="btn btn-mini getMockup" projectId="{id}">Mockups</button></td>
          </tr>
          {/projects}
        </tbody>
        </table>
        