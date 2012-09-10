        <a href="sprints.htm?projectId={projectId}">&larr; Back to Sprints</a><br><br>
        <button id="add" type="submit" class="btn btn-primary btn-mini" sprintId="{sprintId}">Add new</button><br><br>
        <h3>User Stories</h3>
        <table class="table table-condensed">
        <thead>
          <tr>
            <th>Title</th>
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {#userStories}
          <tr>
            <td>{title}</td>
            <td><button type="submit" class="btn btn-mini modifyUserStory" userStoryId="{id}" sprintId="{sprintId}">Modify</button></td>
            <td><button type="submit" class="btn btn-mini getMockup" userStoryId="{id}">Mockups</button></td>
          </tr>
          {/userStories}
        </tbody>
        </table>
        