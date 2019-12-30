Rxjava tutorials
using below tag we can trigger job from jenkin
---------------------------------------------------------
job('rxJavaTutorials') {
  scm {
        github 'CPattanayak/rxJavaTutorials'
    }
   triggers {
     githubPush()
  }
  steps {
    maven {
      goals('-e clean package')
      mavenOpts('-Xms256m')
      mavenOpts('-Xmx512m')
      properties skipTests: true
      mavenInstallation('maven')
    }
  }
}
