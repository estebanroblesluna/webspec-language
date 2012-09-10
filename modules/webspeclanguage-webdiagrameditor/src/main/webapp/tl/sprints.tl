        <a href="projects.htm">&larr; Back to projects</a><br><br>
        <button id="add" type="submit" class="btn btn-primary btn-mini" projectId="{projectId}" >Add new</button><br><br>
        <h3>Sprints</h3>
        <table class="table table-condensed">
        <thead>
          <tr>
            <th>Name</th>
            <th>Sprint number</th>
            <th>Start date</th>
            <th>End date</th>
          </tr>
        </thead>
        <tbody>
          {#sprints}
          <tr>
            <td>{title}</td>
            <td>{number}</td>
            <td>{initDate}</td>
            <td>{endDate}</td>

            <td><button type="submit" class="btn btn-mini modifySprint" sprintId="{id}">Modify</button></td>
            <td><button type="submit" class="btn btn-mini getUserStory" sprintId="{id}">User Stories</button></td>
            <td><button type="submit" class="btn btn-mini getMockup" sprintId="{id}">Mockups</button></td>
          </tr>
          {/sprints}
        </tbody>
        </table>
        