# WSEE Pack Util
See: [WorldSeedEntityEngine](https://github.com/AtlasEngineCa/WorldSeedEntityEngine)

Simple program used before zipping a resource pack to inject the .bbmodels and generate any other files needed for the WSEE library on the server.


## Usage
`java -jar WSEEPackUtil-1.0.jar [input]`

`[input]`: Optional input resource pack directory.  Default `./resourcepack`

.bbmodels input directory: `./wsee_entity_bbmodels`

### Output Files
`wsee_injected_resourcepack`: Your resource pack with entity models added.

`wsee_models_out`: Model files required for WSEE library on server.

`wsee_mappings_out.json`: Mapping file required for WSEE library on server.

### WSEE Server Side
Place both the `wsee_models_out` and `wsee_mappings_out.json` files in the `wsee/` folder of your server.

## Workflow Tips
If you use a CI system like GitHub Actions you could automatically release the WSEE model files alongside the pack.
The server can download these files on startup and use them to load the models.

## Example
See: [EXAMPLE_USAGE.bat](EXAMPLE_USAGE.bat)