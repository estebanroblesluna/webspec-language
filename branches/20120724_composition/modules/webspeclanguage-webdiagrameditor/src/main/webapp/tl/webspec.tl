        <button id="add" type="submit" class="btn btn-primary btn-mini">Add new</button>
        <table class="table table-condensed">
        <thead>
          <tr>
            <th>Diagram</th>
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {#diagrams}
          <tr>
            <td>{name}</td>
            <td><button type="submit" class="btn btn-mini modifyDiagram" diagramId="{id}">Modify</button></td>
            <td><button type="submit" class="btn btn-mini deleteDiagram" diagramId="{id}">Delete</button></td>
          </tr>
          {/diagrams}
        </tbody>
        </table>
        